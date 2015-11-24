<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script>

    require(['angular', 'angular.ui.tree', 'validator.bootstrap', 'underscore', 'util'], function(angular, tree, $, _, util){
        $(function(){
            $("#form1").validate({
                rules: {
                        roleName : {
                        required: true
                    }

                },
                messages: {
                    roleName : {
                        required: ' '
                    }
                }
            });
        });


        angular.module('app', ['ui.tree']).
                controller('rescSelector', function($scope, $http){
                    $scope.rescs=${rescJson};
                    $scope.rescIds=[<c:forEach var="res" items="${item.rescs}" varStatus="status">${res.id}<c:if test="${fn:length(item.rescs)-1 > status.index}">,</c:if></c:forEach>];

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

                    $scope.iteratesResc($scope.rescs, function (val, index, parent) {
                        val.check=$scope.rescIds.indexOf(val.id)!=-1;
                    });




                    $scope.checkResc = function(resc){
                        if(resc.check==null || resc.check==false){
                            //选中当前节点及父节点
                            if($scope.rescIds.indexOf(resc.id)==-1) {
                                $scope.rescIds.push(resc.id);
                            }
                            resc.check=true;
                            if(resc.parentId!=0){
                                var pId=resc.parentId;
                                while(pId!=0) {
                                    $scope.iteratesResc($scope.rescs, function (val, index, parent) {
                                        if (val.id == pId) {
                                            if($scope.rescIds.indexOf(val.id)==-1) {
                                                $scope.rescIds.push(val.id);
                                            }

                                            val.check = true;
                                            pId = val.parentId;
                                        }
                                    });
                                }
                            }
                        }else{
                            //取消当前节点及子节点
                            $scope.rescIds = _.without($scope.rescIds, resc.id);
                            resc.check=false;
                            if(resc.subResource.length>=1) {
                                $scope.iteratesResc(resc.subResource, function (val) {
                                    val.check = false;
                                    $scope.rescIds = _.without($scope.rescIds, val.id);
                                });
                            }
                        }
                    }

                    $scope.mysubmit = function(){
                        console.log("submit");
                        $("#form1").submit();
                        return true;
                    }

        });
        angular.bootstrap(document.getElementById("ngapp"), ['app']);
    });
</script>

<style>
    .angular-ui-tree-handle {
        padding-top: 3px;
    }
</style>

<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="sys_role_list.html">系统角色列表</a></li>
        <feinno-security2:hasPermission name="/module/security/sys_role_add.html">
        <li<c:if test="${empty item}"> class="active"</c:if>><a href="sys_role_add.html"><i class="fa fa-plus"></i> 增加系统角色</a></li>
        </feinno-security2:hasPermission>
        <c:if test="${not empty item}"><li class="active"><a href="javascript:"><i class="fa fa-edit"></i> 编辑系统角色</a></li></c:if>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal form" method="post" id="form1">
            <c:if test="${not empty item}">
            <input type="hidden" name="id" value="${item.id}" />
            </c:if>
            <div class="panel panel-default">
                <div class="panel-heading">
                    系统角色
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">角色名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="roleName" name="roleName" placeholder="请输入角色名称"  value="${item.roleName}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">描述信息</label>
                        <div class="col-sm-9">
                            <textarea name="descn" rows="5" class="form-control" id="descn" placeholder="请输入描述信息">${item.descn}</textarea>

                        </div>
                    </div>


                    <div class="form-group ng-scope ng-cloak" id="ngapp">
                        <script type="text/ng-template" id="nodes_renderer.html">
                            <div ui-tree-handle class="tree-node tree-node-content">
                                <%--<a class="btn btn-success btn-xs" data-nodrag ng-click="toggle(this)"><span class="glyphicon" ng-class="{'glyphicon-chevron-right': collapsed, 'glyphicon-chevron-down': !collapsed}"></span></a>--%>
                                <a href="javascript:;" ng-click="checkResc(resc)" ng-class="{'btn-success' : resc.check, 'btn-default' : !resc.check}" class="btn btn-sm">{{resc.title}}</a>
                            </div>
                            <ol ui-tree-nodes="" ng-model="resc.subResource" ng-class="{hidden: collapsed}">
                                <li ng-repeat="resc in resc.subResource" ui-tree-node ng-include="'nodes_renderer.html'">
                                </li>
                            </ol>
                        </script>
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">拥有的资源</label>
                        <div class="col-sm-9" ng-controller="rescSelector">
                            <input type="hidden" name="resIds[]" ng-repeat="rescId in rescIds" ng-model="rescId" value="{{rescId}}" />
                            <div class="panel-body" id="tree-root" ui-tree data-drag-enabled="false">
                                <ol ui-tree-nodes ng-model="rescs">
                                    <li ng-repeat="resc in rescs" ui-tree-node ng-include="'nodes_renderer.html'"></li>
                                </ol>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <button type="submit" class="btn btn-primary col-lg-1" name="submit" value="提交">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>