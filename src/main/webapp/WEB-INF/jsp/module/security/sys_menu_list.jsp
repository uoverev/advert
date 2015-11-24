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
                //$httpProvider.defaults.headers.get['x-requested-with'] = 'XMLHttpRequest';
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
            }).controller('menuDesigner', function($scope, $http){
                $scope.menus = ${menuJson};
                $scope.activeMenu = {};
                $scope.copyActiveMenu={};
                $scope.dialog={title:""};
                $scope.activeResTitle="";

                $scope.rescs=${rescJson};
                $scope.activeRescs=[];

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

                $scope.checkResc = function(resc){
                    $scope.iteratesResc($scope.activeRescs, function (val) {
                        val.check = false;
                    });

                    resc.check=true;
                    $scope.activeMenu.resourceId=resc.id;

                }


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

                //选择图标
                $scope.showIconDialog = function(elm) {
                    var btn = $(elm);
                    var spview = btn.parent().prev();
                    var ipt = spview.prev();
                    if(!ipt.val()){
                        spview.css("display","none");
                    }

                    util.iconBrowser(function(ico){
                        $scope.activeMenu.icon=ico;
                        ipt.val(ico);
                        spview.show();
                        spview.find("i").attr("class","");
                        spview.find("i").addClass(ico);

                    });
                }

                //重置对象
                $scope.resetDialog = function (){
                    for(key in $scope.activeMenu){
                        $scope.activeMenu[key] = $scope.copyActiveMenu[key];
                    }
                }

                $scope.showViewDialog = function(menu){
                    $scope.activeResTitle="";
                    $scope.iteratesResc($scope.activeRescs, function (val) {
                        if(menu.resourceId==val.id){
                            $scope.activeResTitle = val.title;
                        }
                    });
                    $scope.activeMenu=menu;
                    $scope.dialog.title="查看"+(menu.parentId==0 ? "一级" : "二级")+"【"+menu.title+"】菜单";
                    $('#view_dialog').modal('show');
                }

                //打开操Dialog
                $scope.showDetailDialog = function(menu) {
                    if(menu.parentId!=0){
                        $scope.activeRescs=angular.copy($scope.rescs);
                    }
                    if(menu.resourceId!=""){
                        $scope.iteratesResc($scope.activeRescs, function (val) {
                            if(menu.resourceId==val.id){
                                val.check = true;
                            }
                        });
                    }
                    $scope.copyActiveMenu = angular.copy(menu);
                    $scope.activeMenu=menu;
                    if(menu.id==""){
                        $scope.dialog.title="增加"+(menu.parentId==0 ? "一级" : "二级")+"菜单";
                    }else{
                        $scope.dialog.title="编辑"+(menu.parentId==0 ? "一级" : "二级")+"【"+menu.title+"】菜单";
                    }
                    $('#dialog').modal('show');
                };

                //增加菜单
                $scope.addMenu = function(menu) {
                    if(menu==null){
                        $scope.activeMenu={
                                    id: "", //ID
                                    parentId:0, //父id
                                    title:"",
                                    icon:"",
                                    showMode:2,//默认展开
                                    descn:"",//描述
                                    status:1,//状态
                                    resourceId:"",//资源ID
                                    createDate:"",//创建时间
                                    subMenus:[]
                        };
                    } else {
                        $scope.activeMenu={
                            id: "", //ID
                            parentId:menu.id, //父id
                            title:"",
                            icon:"",
                            showMode:2,//默认展开
                            descn:"",//描述
                            status:1,//状态
                            resourceId:"",//资源ID
                            createDate:""//创建时间
                        }
                    }

                    $scope.showDetailDialog($scope.activeMenu);

                };

                //删除菜单
                $scope.deleteMenu = function(menu) {
                    if(!confirm('将删除菜单，'+(menu.subMenus && menu.subMenus.length > 0 ? '同时删除所有子菜单,' : '') + '是否继续? ')){
                        return;
                    }

                    console.log($.param(menu));
                    $http.get('${pageContext.request.contextPath }/module/security/sys_menu_del.html',
                            {params: {id : menu.id}}).success(
                            function(dat, status){
                                if(menu.parentId==0){
                                    $scope.menus = _.without($scope.menus, menu);
                                }else{
                                    angular.forEach($scope.menus, function(val){
                                        angular.forEach(val.subMenus, function(sub){
                                            if(sub.id==menu.id){
                                                val.subMenus = _.without(val.subMenus, menu);
                                                return;
                                            }
                                        });
                                    });
                                }
                            });
                };

                //保存菜单
                $scope.saveMenu = function(valid){
                    if(valid==false) {
                        console.log(valid);
                        return;
                    }
                    var menu = {
                        id : $scope.activeMenu.id,
                        parentId : $scope.activeMenu.parentId,
                        title : $scope.activeMenu.title,
                        icon : $scope.activeMenu.icon,
                        showMode : $scope.activeMenu.showMode,
                        descn : $scope.activeMenu.descn,
                        sortNum : $scope.activeMenu.sortNum,
                        status : $scope.activeMenu.status,
                        resourceId : $scope.activeMenu.resourceId
                    };

                    if($scope.activeMenu.id==""){
                        //计算排序值
                        var menuList=[];
                        if($scope.activeMenu.parentId==0){
                            menuList = $scope.menus;
                        }else{
                            angular.forEach($scope.menus, function(val){
                                if(val.id==$scope.activeMenu.parentId){
                                    val.subMenus = val.subMenus ? val.subMenus : [];
                                    menuList=val.subMenus;
                                    return;
                                }
                            });
                        }
                        //设置排序值
                        $scope.activeMenu.sortNum = menuList.length+1;
                        menu.sortNum=$scope.activeMenu.sortNum;
                        //增加
                        $http.post('${pageContext.request.contextPath }/module/security/sys_menu_add.html',
                                $.param(menu)).success(
                                    function(dat, status){
                                        if(dat.success) {
                                            //页面上增加菜单
                                            $scope.activeMenu.id=dat.data.id;
                                            menuList.push($scope.activeMenu);
                                            console.log($scope.activeMenu);
                                        } else {
                                            if(util.isAuthCode(dat.code)){
                                                util.message(dat.message, '', 'error');
                                            } else {
                                                util.message('增加菜单失败', '', 'error');
                                            }
                                        }
                                    });
                    }else{
                        //修改
                        console.log($.param(menu));
                        $http.post('${pageContext.request.contextPath }/module/security/sys_menu_update.html',
                                $.param(menu)).success(
                                    function(dat, status){
                                        console.log($.param(dat));
                                        if(!dat.success) {
                                            $scope.resetDialog();
                                            if(util.isAuthCode(dat.code)){
                                                util.message(dat.message, '', 'error');
                                            } else {
                                                util.message('修改菜单失败', '', 'error');
                                            }
                                        }
                                    });
                    }
                    $('#dialog').modal('hide');
                };


                //排序
                $scope.treeOptions = {
                    dragStop: function(event) {
                        var plist={ids:[], sortNums:[], parentIds:[]};
                        angular.forEach($scope.menus, function(menu, index){
                            menu.sortNum=index+1;
                            plist.ids.push(menu.id);
                            plist.sortNums.push(menu.sortNum);
                            plist.parentIds.push(0);
                            if(menu.subMenus){
                                angular.forEach(menu.subMenus, function(sub, index){
                                    sub.sortNum=index+1;
                                    sub.parentId=menu.id;
                                    plist.ids.push(sub.id);
                                    plist.sortNums.push(sub.sortNum);
                                    plist.parentIds.push(sub.parentId);
                                });
                            }
                        });

                        console.log($.param(plist));
                        console.log(plist.parentIds);

                        $http.post('${pageContext.request.contextPath }/module/security/sys_menu_sort.html',
                                $.param(plist)).success(
                                function(dat, status){
                                    console.log($.param(dat));
                                    if(!dat.success){
                                        if(util.isAuthCode(dat.code)){
                                            util.message(dat.message, '', 'error');
                                        } else {
                                            util.message('移动菜单失败', '', 'error');
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

    <div class="clearfix">
        <div class="ng-scope ng-cloak" ng-controller="menuDesigner">
            <div class="panel panel-default">
                <div class="panel-heading">
                    菜单设计器
                    <a href="" class="btn btn-default" ng-click="collapseAll()">全部收缩</a>
                    <a href="" class="btn btn-default" ng-click="expandAll()">全部展开</a>
                </div>
                <div class="panel-body" ui-tree="treeOptions" id="tree-root">
                    <ol ui-tree-nodes ng-model="menus" data-type="group">
                        <li ng-repeat="menu in menus" ui-tree-node>
                            <div class="group-title angular-ui-tree-handle">
                                <feinno-security2:hasPermission name="/module/security/sys_menu_del.html">
                                <a href="" class="btn btn-danger btn-xs pull-right" data-nodrag ng-click="deleteMenu(menu)"><i class="glyphicon glyphicon-remove"></i></a>
                                </feinno-security2:hasPermission>
                                <feinno-security2:hasPermission name="/module/security/sys_menu_update.html">
                                <a href="" class="btn btn-primary btn-xs pull-right" data-nodrag ng-click="showDetailDialog(menu);"><i class="glyphicon glyphicon-pencil"></i></a>
                                </feinno-security2:hasPermission>
                                <feinno-security2:hasPermission name="/module/security/sys_menu_view.html">
                                <a href="" class="btn btn-primary btn-xs pull-right" data-nodrag ng-click="showViewDialog(menu);"><i class="glyphicon glyphicon-eye-open"></i></a>
                                </feinno-security2:hasPermission>
                                <div>
                                    <a class="btn btn-success btn-xs" data-nodrag ng-click="toggle(this)"><span class="glyphicon" ng-class="{'glyphicon-chevron-right': collapsed, 'glyphicon-chevron-down': !collapsed}"></span></a>
                                    &nbsp;{{menu.title}}
                                </div>
                            </div>
                            <ol ui-tree-nodes ng-model="menu.subMenus" data-type="category" ng-class="{hidden: collapsed}">
                                <li ng-repeat="sub in menu.subMenus" ui-tree-node>
                                    <div class="category-title angular-ui-tree-handle">
                                        <feinno-security2:hasPermission name="/module/security/sys_menu_del.html">
                                        <a href="" class="btn btn-danger btn-xs pull-right" data-nodrag ng-click="deleteMenu(sub)"><i class="glyphicon glyphicon-remove"></i></a>
                                        </feinno-security2:hasPermission>
                                        <feinno-security2:hasPermission name="/module/security/sys_menu_update.html">
                                        <a href="" class="btn btn-primary btn-xs pull-right" data-nodrag ng-click="showDetailDialog(sub);"><i class="glyphicon glyphicon-pencil"></i></a>
                                        </feinno-security2:hasPermission>
                                        <feinno-security2:hasPermission name="/module/security/sys_menu_view.html">
                                        <a href="" class="btn btn-primary btn-xs pull-right" data-nodrag ng-click="showViewDialog(sub);"><i class="glyphicon glyphicon-eye-open"></i></a>
                                        </feinno-security2:hasPermission>
                                        <div>
                                            &nbsp;<i class="{{sub.icon}}"></i> {{sub.title}}
                                        </div>
                                    </div>
                                </li>
                            </ol>
                            <feinno-security2:hasPermission name="/module/security/sys_menu_add.html">
                            <ol class="angular-ui-tree-nodes" data-nodrag  ng-class="{hidden: collapsed}">
                                <li class="angular-ui-tree-node">
                                    <div class="category-title angular-ui-tree-handle">
                                        <form class="form-inline" role="form">
                                            <button type="submit" class="btn btn-default" ng-click="addMenu(menu);">增加子菜单</button>
                                        </form>
                                    </div>
                                </li>
                            </ol>
                            </feinno-security2:hasPermission>
                        </li>
                    </ol>
                    <feinno-security2:hasPermission name="/module/security/sys_menu_add.html">
                    <ol class="angular-ui-tree-nodes">
                        <li class="angular-ui-tree-node">
                            <div class="group-title angular-ui-tree-handle">
                                <form class="form-inline" role="form">
                                    <button type="submit" class="btn btn-default" ng-click="addMenu();">增加菜单</button>
                                </form>
                            </div>
                        </li>
                    </ol>
                    </feinno-security2:hasPermission>
                </div>
            </div>




            <div id="dialog" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form class="form-horizontal form" method="post" id="form1" name="form1" ng-submit="saveMenu(form1.$valid)"  novalidate>
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" ng-click="resetDialog();">×</button>
                                <h3 class="ng-binding">{{dialog.title}}</h3>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">菜单名称</label>
                                    <div class="col-sm-9" ng-class="{ 'has-error' : form1.title.$invalid && !form1.title.$pristine }">
                                        <input type="text" class="form-control" id="title" name="title" placeholder="请输入菜单名称" required ng-model="activeMenu.title">
                                        <p ng-show="form1.title.$invalid && !form1.title.$pristine" class="help-block">菜单名称必须输入.</p>
                                    </div>
                                </div>

                                <div class="form-group" ng-show="activeMenu.parentId > 0;">
                                    <label class="col-sm-2 control-label">菜单图标</label>
                                    <div class="col-sm-3">
                                        <div class="input-group">
                                            <div class="input-group" style="width: 300px;">
                                                <input type="text" name="icon" class="form-control" autocomplete="off" ng-model="activeMenu.icon">
                                                <span class="input-group-addon" style="display:none"><i class=" fa"></i></span>
                                                <span class="input-group-btn">
                                                    <button class="btn btn-default" type="button" ng-click="showIconDialog($event.target);">选择图标</button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group" ng-show="activeMenu.parentId == 0;">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">子菜单</label>

                                    <div class="col-sm-6">
                                        <label class="radio-inline">
                                            <input type="radio" name="showMode" value="1" ng-model="activeMenu.showMode"> 默认展开
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="showMode" value="2" ng-model="activeMenu.showMode"> 默认隐藏
                                        </label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">状态</label>

                                    <div class="col-sm-4">
                                        <label class="radio-inline">
                                            <input type="radio" name="status" value="1" ng-model="activeMenu.status"> 启用
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="status" value="0" ng-model="activeMenu.status"> 停用
                                        </label>
                                    </div>
                                </div>

                                <div class="form-group" ng-show="activeMenu.parentId > 0;">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">资源</label>
                                    <script type="text/ng-template" id="nodes_renderer.html">
                                        <div ui-tree-handle style="padding: 3px 0px; border: 0px;">
                                            <%--<a class="btn btn-success btn-xs" data-nodrag ng-click="toggle(this)"><span class="glyphicon" ng-class="{'glyphicon-chevron-right': collapsed, 'glyphicon-chevron-down': !collapsed}"></span></a>--%>
                                            <a href="javascript:;" ng-click="checkResc(resc)" ng-class="{'btn-success' : resc.check, 'btn-default' : !resc.check}" class="btn btn-sm">{{resc.title}}</a>
                                        </div>
                                        <ol ui-tree-nodes="" ng-model="resc.subResource" ng-class="{hidden: collapsed}">
                                            <li ng-repeat="resc in resc.subResource" ui-tree-node ng-include="'nodes_renderer.html'">
                                            </li>
                                        </ol>
                                    </script>
                                    <div class="col-sm-9">
                                        <div id="tree-rescs" ui-tree data-drag-enabled="false">
                                            <ol ui-tree-nodes ng-model="activeRescs">
                                                <li ng-repeat="resc in activeRescs" ui-tree-node ng-include="'nodes_renderer.html'"></li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-12 col-sm-3 col-md-2 control-label">备注</label>
                                    <div class="col-sm-6 col-md-8">
                                        <textarea style="height:100px;" class="form-control basic-content-new" cols="50" id="descn" name="descn" autocomplete="off" ng-model="activeMenu.descn"></textarea>
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
                                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">菜单名称</label>
                                        <div class="col-sm-9">
                                            <p class="form-control-static">
                                                {{activeMenu.title}}
                                            </p>
                                        </div>
                                    </div>

                                    <div class="form-group" ng-show="activeMenu.parentId > 0;">
                                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">菜单图标</label>
                                        <div class="col-sm-9">
                                            <p class="form-control-static">
                                                <i ng-class="activeMenu.icon"></i>
                                            </p>
                                        </div>
                                    </div>

                                    <div class="form-group" ng-show="activeMenu.parentId == 0;">
                                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">子菜单</label>
                                        <div class="col-sm-9">
                                            <p class="form-control-static">
                                                {{activeMenu.showMode==1 ? '默认展开' : '默认隐藏'}}
                                            </p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">状态</label>
                                        <div class="col-sm-9">
                                            <p class="form-control-static">
                                                {{activeMenu.status==1 ? '启用' : '停用'}}
                                            </p>
                                        </div>
                                    </div>

                                    <div class="form-group" ng-show="activeMenu.parentId > 0;">
                                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">资源</label>
                                        <div class="col-sm-9">
                                            <p class="form-control-static">
                                                {{activeResTitle}}
                                            </p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">备注</label>
                                        <div class="col-sm-9">
                                            <p class="form-control-static">
                                                {{activeMenu.descn}}
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

