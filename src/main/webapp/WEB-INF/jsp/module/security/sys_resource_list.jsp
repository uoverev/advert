<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <script type="text/javascript">

        require(['angular', 'angular.ui.tree', 'jquery', 'underscore', 'util'], function(angular, tree, $, _, util){
            angular.module('app', ['ui.tree'], function($httpProvider) {
                <%-- angular 的 http post ， spring controller 不能正常接收参数， 通过 此方法扩展，
                在 post 用 jquery.param(json) 序列化 json 参数后可 controller 正常接收参数--%>
                // Use x-www-form-urlencoded Content-Type
                $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
                // 设置为ajax提交
                $httpProvider.defaults.headers.common['x-requested-with'] = 'XMLHttpRequest';
                /**
                 * The workhorse; converts an object to x-www-form-urlencoded serialization.
                 * @param {Object} obj
                 * @return {String}
                 */
                var param = function(obj) {
                    var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

                    for(name in obj) {
                        value = obj[name];

                        if(value instanceof Array) {
                            for(i=0; i<value.length; ++i) {
                                subValue = value[i];
                                fullSubName = name + '[' + i + ']';
                                innerObj = {};
                                innerObj[fullSubName] = subValue;
                                query += param(innerObj) + '&';
                            }
                        }
                        else if(value instanceof Object) {
                            for(subName in value) {
                                subValue = value[subName];
                                fullSubName = name + '[' + subName + ']';
                                innerObj = {};
                                innerObj[fullSubName] = subValue;
                                query += param(innerObj) + '&';
                            }
                        }
                        else if(value !== undefined && value !== null)
                            query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
                    }

                    return query.length ? query.substr(0, query.length - 1) : query;
                };

                // Override $http service's default transformRequest
                $httpProvider.defaults.transformRequest = [function(data) {
                    return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
                }];
            }).controller('resourceDesigner', function($scope, $http){

                $scope.resources = ${resources};
                $scope.activeResource = {};
                $scope.copyActiveResource={};
                $scope.dialog={title:""};

                $scope.resTypes=[
                    {title : "连接", value:"URL"},
                    {title : "按钮", value:"BUTTON"}
                ]

                var getRootNodesScope = function() {
                    return angular.element(document.getElementById("tree-root")).scope();
                };

                $scope.collapseAll = function() {
                    var scope = getRootNodesScope();
                    scope.collapseAll();
                };

                $scope.expandAll = function() {
                    var scope = getRootNodesScope();
                    scope.expandAll();
                };

                //重置对象
                $scope.resetDialog = function (){
                    for(key in $scope.activeResource){
                        $scope.activeResource[key] = $scope.copyActiveResource[key];
                    }
                }

                $scope.showViewDialog = function(resc){
                    $scope.activeResource=resc;
                    $scope.dialog.title="查看【"+resc.title+"】资源";
                    $('#view_dialog').modal('show');
                }

                //打开操Dialog
                $scope.showDetailDialog = function(resc) {
                    $scope.copyActiveResource = angular.copy(resc);
                    $scope.activeResource=resc;
                    if(resc.id==""){
                        $scope.dialog.title="增加资源";
                    }else{
                        $scope.dialog.title="编辑资源";
                    }
                    $('#dialog').modal('show');
                };

                //增加菜单
                $scope.addResc = function(resource) {
                        $scope.activeResource={
                            id: "", //ID
                            parentId: resource ? resource.id : 0, //父id
                            title:"",
                            restype:"URL",
                            resString:"",//默认展开
                            permissionValue:"",
                            descn:"",//描述
                            status:1,//状态
                            sortNum:resource ? resource.subResource.length+1 : $scope.resources.length+1,//资源ID
                            createDate:"",//创建时间
                            subResource:[]
                        };
                    $scope.showDetailDialog($scope.activeResource);

                };

                $scope.iteratesResc = function(rescs, callback, parent, level){
                    level = level ? 1 : level+1;
                    for(var j=0; j<rescs.length; j++){
                        var val = rescs[j];
                        callback(val, j, parent, level);
                        if(val.subResource.length>0){
                            $scope.iteratesResc(val.subResource, callback, val, level);
                        }
                    }
                }

                $scope.saveResc = function(valid) {
                    if (valid == false) {
                        console.log(valid);
                        return;
                    }
                    var resource = {
                        id : $scope.activeResource.id,
                        parentId : $scope.activeResource.parentId,
                        title : $scope.activeResource.title,
                        restype : $scope.activeResource.restype,
                        resString : $scope.activeResource.resString,
                        permissionValue : $scope.activeResource.permissionValue,
                        descn : $scope.activeResource.descn,
                        status : $scope.activeResource.status,
                        sortNum : $scope.activeResource.sortNum
                    };
                    if($scope.activeResource.id==""){
                        //增加
                        $scope.activeResource.id= $scope.rescId++;
                        $http.post('${pageContext.request.contextPath }/module/security/sys_resource_add.html',
                                $.param(resource)).success(
                                function(dat, status){
                                    if(dat.success) {
                                        //页面上增加数据
                                        $scope.activeResource.id=dat.data.id;
                                        if($scope.activeResource.parentId!=0){
                                            $scope.iteratesResc($scope.resources, function(resc, index){
                                                if(resc.id==$scope.activeResource.parentId){
                                                    resc.subResource.push($scope.activeResource);
                                                }
                                            });
                                        } else {
                                            $scope.resources.push($scope.activeResource);
                                        }
                                    } else {
                                        if(util.isAuthCode(dat.code)){
                                            util.message(dat.message, '', 'error');
                                        } else {
                                            util.message('增加资源失败', '', 'error');
                                        }

                                    }
                                });
                    }else{
                        //修改
                        $http.post('${pageContext.request.contextPath }/module/security/sys_resource_update.html',
                                $.param(resource)).success(
                                function(dat, status){
                                    if(!dat.success) {
                                        $scope.resetDialog();
                                        if(util.isAuthCode(dat.code)){
                                            util.message(dat.message, '', 'error');
                                        } else {
                                            util.message('修改资源失败', '', 'error');
                                        }
                                    }
                                });
                    }
                    console.log($scope.activeResource);
                    $('#dialog').modal('hide');
                }

                $scope.deleteResc = function(resource){
                    if(!confirm('将删除资源，'+(resource.subResource.length > 0 ? '同时删除所有子资源,' : '') + '是否继续? ')){
                        return;
                    }
                    var params={ids:[resource.id]};
                    $scope.iteratesResc(resource.subResource, function(resc){
                        params.ids.push(resc.id);
                    });
                    console.log(params.ids);
                    $http.post('${pageContext.request.contextPath }/module/security/sys_resource_del.html',
                            $.param(params)).success(
                            function(dat, status){
                                if(dat.success){
                                    if(resource.parentId!=0){
                                        $scope.iteratesResc($scope.resources, function(resc){
                                            if(resc.id==resource.parentId){
                                                resc.subResource = _.without(resc.subResource, resource);
                                            }
                                        });
                                    }else{
                                        $scope.resources = _.without($scope.resources, resource);
                                    }
                                }else{
                                    if(util.isAuthCode(dat.code)){
                                        util.message(dat.message, '', 'error');
                                    } else {
                                        util.message('删除资源失败', '', 'error');
                                    }
                                }
                            });

                }


                //排序
                $scope.treeOptions = {
                    dragStop: function(event) {
                        var plist={ids:[], sortNums:[], parentIds:[]};
                        $scope.iteratesResc($scope.resources, function(resc, index, parent){
                            resc.sortNum=index+1;
                            resc.parentId=parent ? parent.id : 0;
                            plist.ids.push(resc.id);
                            plist.sortNums.push(resc.sortNum);
                            plist.parentIds.push(resc.parentId);
                        });

                        console.log($.param(plist));
                        console.log(plist);

                        $http.post('${pageContext.request.contextPath }/module/security/sys_resource_sort.html',
                                $.param(plist)).success(
                                function(dat, status){
                                    console.log($.param(dat));
                                    if(!dat.success){
                                        if(util.isAuthCode(dat.code)){
                                            util.message(dat.message, '', 'error');
                                        } else {
                                            util.message('移动资源失败', '', 'error');
                                        }
                                    }
                                });


                    }
                };

            });
            angular.bootstrap(document, ['app']);

            $(function(){
                $('#dialog').modal({backdrop: 'static', keyboard: false, show: false});
            });
        });

    </script>

    <style>
        .btn {
            margin-right: 8px;
        }

        .angular-ui-tree-handle {
            background: #f8faff;
            border: 1px solid #dae2ea;
            color: #7c9eb2;
            padding: 10px 10px;
        }

        .angular-ui-tree-handle:hover {
            color: #438eb9;
            background: #f4f6f7;
            border-color: #dce2e8;
        }

        .angular-ui-tree-placeholder {
            background: #f0f9ff;
            border: 2px dashed #bed2db;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }


        .group-title {
            background-color: #687074 !important;
            color: #FFF !important;
        }
    </style>

    <script type="text/ng-template" id="nodes_renderer.html">
        <div ui-tree-handle class="tree-node tree-node-content">
            <a class="btn btn-success btn-xs" data-nodrag ng-click="toggle(this)"><span class="glyphicon" ng-class="{'glyphicon-chevron-right': collapsed, 'glyphicon-chevron-down': !collapsed}"></span></a>
            {{resc.title}}
            <feinno-security2:hasPermission name="/module/security/sys_resource_del.html">
            <a class="pull-right btn btn-danger btn-xs" data-nodrag ng-click="deleteResc(resc);"><span class="glyphicon glyphicon-remove"></span></a>
            </feinno-security2:hasPermission>
            <feinno-security2:hasPermission name="/module/security/sys_resource_update.html">
            <a class="pull-right btn btn-primary btn-xs" data-nodrag ng-click="showDetailDialog(resc);"><i class="glyphicon glyphicon-pencil"></i></a>
            </feinno-security2:hasPermission>
            <feinno-security2:hasPermission name="/module/security/sys_resource_add.html">
            <a class="pull-right btn btn-primary btn-xs" data-nodrag ng-click="addResc(resc);"><span class="glyphicon glyphicon-plus"></span></a>
            </feinno-security2:hasPermission>
            <feinno-security2:hasPermission name="/module/security/sys_resource_view.html">
            <a class="pull-right btn btn-primary btn-xs" data-nodrag ng-click="showViewDialog(resc);" style="margin-right: 8px;"><i class="glyphicon glyphicon-eye-open"></i></a>
            </feinno-security2:hasPermission>
        </div>
        <ol ui-tree-nodes="" ng-model="resc.subResource" ng-class="{hidden: collapsed}">
            <li ng-repeat="resc in resc.subResource" ui-tree-node ng-include="'nodes_renderer.html'">
            </li>
        </ol>
    </script>



    <div class="clearfix">
        <div class="ng-scope ng-cloak" ng-controller="resourceDesigner">
            <div class="panel panel-default">
                <div class="panel-heading">
                    资源管理
                    <a href="" class="btn btn-default" ng-click="collapseAll()">全部收缩</a>
                    <a href="" class="btn btn-default" ng-click="expandAll()">全部展开</a>
                    <feinno-security2:hasPermission name="/module/security/sys_resource_add.html">
                    <a href="" class="btn btn-default btn-warning" ng-click="addResc()">增加资源</a>
                    </feinno-security2:hasPermission>
                </div>
                <div class="panel-body" id="tree-root" ui-tree="treeOptions">
                    <ol ui-tree-nodes ng-model="resources">
                        <li ng-repeat="resc in resources" ui-tree-node ng-include="'nodes_renderer.html'"></li>
                    </ol>
                </div>
            </div>




            <div id="dialog" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form class="form-horizontal form" method="post" id="form1" name="form1" ng-submit="saveResc(form1.$valid)"  novalidate>
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" ng-click="resetDialog();">×</button>
                                <h3 class="ng-binding">{{dialog.title}}</h3>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">资源名称</label>
                                    <div class="col-sm-9" ng-class="{ 'has-error' : form1.title.$invalid && !form1.title.$pristine }">
                                        <input type="text" class="form-control" id="title" name="title" placeholder="请输入资源名称" required ng-model="activeResource.title">
                                        <p ng-show="form1.title.$invalid && !form1.title.$pristine" class="help-block">资源名称必须输入.</p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">资源类型</label>
                                    <div class="col-sm-9" ng-class="{ 'has-error' : form1.title.$invalid && !form1.title.$pristine }">
                                        <select ng-model="activeResource.restype" ng-options="o.value as o.title for o in resTypes"></select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">资源值</label>
                                    <div class="col-sm-9" ng-class="{ 'has-error' : form1.resString.$invalid && !form1.resString.$pristine }">
                                        <input type="text" class="form-control" id="resString" name="resString" placeholder="请输入资源值" required ng-model="activeResource.resString">
                                        <p ng-show="form1.resString.$invalid && !form1.resString.$pristine" class="help-block">资源值必须输入.</p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">权限值</label>
                                    <div class="col-sm-9" ng-class="{ 'has-error' : form1.permissionValue.$invalid && !form1.permissionValue.$pristine }">
                                        <input type="text" class="form-control" id="permissionValue" name="permissionValue" placeholder="请输入资源值" required ng-model="activeResource.permissionValue">
                                        <p ng-show="form1.permissionValue.$invalid && !form1.permissionValue.$pristine" class="help-block">资源值必须输入.</p>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">状态</label>

                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" value="1" ng-model="activeResource.status"> 启用
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" value="0" ng-model="activeResource.status"> 停用
                                        </label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">备注</label>
                                    <div class="col-sm-6 col-md-8">
                                        <textarea style="height:100px;" class="form-control basic-content-new" cols="50" id="descn" name="descn" autocomplete="off" ng-model="activeResource.descn"></textarea>
                                    </div>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary" ng-disabled="form1.$invalid" >保存</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="resetDialog();">取消</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>










            <div id="view_dialog" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h3 class="ng-binding">{{dialog.title}}</h3>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal form">
                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">资源名称</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">
                                            {{activeResource.title}}
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">资源类型</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">
                                            {{activeResource.restype=='URL' ? '链接' : '按钮'}}
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">资源值</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">
                                            {{activeResource.resString}}
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">权限值</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">
                                            {{activeResource.permissionValue}}
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">状态</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">
                                            {{activeResource.status==1 ? '启用' : '停用'}}
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">备注</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static">
                                            {{activeResource.descn}}
                                        </p>
                                    </div>
                                </div>
                            </form>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- 错误信息 -->
    <div id="errorinfo"></div>
</div>

