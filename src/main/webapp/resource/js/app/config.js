require.config({
    urlArgs: "bust=" + (new Date()).getTime(),
	baseUrl: '/resource/js/app',
	paths: {
		'jquery': '../lib/jquery-1.11.1.min',
		'jquery.ui': '../lib/jquery-ui-1.10.3.min',
		'jquery.caret': '../lib/jquery.caret',
		'jquery.jplayer': '../../components/jplayer/jquery.jplayer.min',
		'jquery.zclip': '../../components/zclip/jquery.zclip.min',
		'bootstrap': '../lib/bootstrap.min',
		'bootstrap.switch': '../../components/switch/bootstrap-switch.min',
		'angular': '../lib/angular.min',
		'angular.sanitize': '../lib/angular-sanitize.min',
        'angular.ui.sortable' : '../lib/angular-ui-sortable',
        'angular.ui.tree' : '../../components/angularuitree/angular-ui-tree',
		'underscore': '../lib/underscore-min',
		'chart': '../lib/chart.min',
		'moment': '../lib/moment',
		'filestyle': '../lib/bootstrap-filestyle.min',
		'datetimepicker': '../../components/datetimepicker/bootstrap-datetimepicker.min',
		'daterangepicker': '../../components/daterangepicker/daterangepicker',
		'colorpicker': '../../components/colorpicker/spectrum',
		//'map': 'http://api.map.baidu.com/getscript?v=2.0&ak=F51571495f717ff1194de02366bb8da9&services=&t=20140530104353',
        'map': 'http://api.map.baidu.com/getscript?v=2.0&ak=F51571495f717ff1194de02366bb8da9&services=&t=20140530104353',
		'editor': '../../components/tinymce/tinymce.min',
		'kindeditor':'../../components/kindeditor/lang/zh_CN',
		'kindeditor.main':'../../components/kindeditor/kindeditor-min',
		'css': '../lib/css.min',
        'jquery.validate' : '../lib/jquery-validate-1.12.min',
        'validator.bootstrap' : '../lib/jquery-validate-expand',
        'jquery.ticker' : '../../components/ticker/jquery.easy-ticker.min',
        'bootstrap.select': '../lib/bootstrap-select.min',
        'emoji' : '../../components/emoji/emoji',
        'summernote': '../lib/summernote',
        'summernote.lang':'../lib/summernote-zh-CN',
        'bootstrapQ':'../lib/bootstrapQ.min',
        'ConfirmWindow':'../lib/ConfirmWindow'
	},
	shim:{
		'jquery.ui': {
			exports: "$",
			deps: ['jquery']
		},
		'jquery.caret': {
			exports: "$",
			deps: ['jquery']
		},
		'jquery.jplayer': {
			exports: "$",
			deps: ['jquery']
		},
		'bootstrap': {
			exports: "$",
			deps: ['jquery']
		},
		'bootstrap.switch': {
			exports: "$",
			deps: ['bootstrap', 'css!../../components/switch/bootstrap-switch.min.css']
		},
		'angular': {
			exports: 'angular',
			deps: ['jquery']
		},
		'angular.sanitize': {
			exports: 'angular',
			deps: ['angular']
		},
		'emotion': {
			deps: ['jquery']
		},
		'chart': {
			exports: 'Chart'
		},
		'filestyle': {
			exports: '$',
			deps: ['bootstrap']
		},
		'datetimepicker': {
			exports: '$',
			deps: ['bootstrap', 'css!../../components/datetimepicker/bootstrap-datetimepicker.min.css']
		},
		'daterangepicker': {
			exports: '$',
			deps: ['bootstrap', 'moment', 'css!../../components/daterangepicker/daterangepicker.css']
		},
		'kindeditor': {
			deps: ['kindeditor.main', 'css!../../components/kindeditor/themes/default/default.css']
		},
		'colorpicker': {
			exports: '$',
			deps: ['css!../../components/colorpicker/spectrum.css']
		},
		'map': {
			exports: 'BMap'
		},
        'validator.bootstrap' : {
            exports : '$',
            deps : ['jquery', 'jquery.validate']
        },
        'jquery.ticker' : {
            exports: "$",
            deps: ['jquery']
        },
        'bootstrap.select':{
        	exports : '$',
            deps : ['jquery', 'bootstrap', 'css!../../css/bootstrap-select.min.css']
        },
        'emoji' : {
            exports : 'emoji',
            deps :['jquery', 'css!../../components/emoji/emoji/emoji.css']
        },
        'angular.ui.sortable' : {
            deps :['jquery.ui', 'angular']
        },
        'angular.ui.tree' : {
            deps : ['angular', 'css!../../components/angularuitree/angular-ui-tree.min.css']
        },
        'summernote' : {
        	deps :['bootstrap','css!../../css/summernote.css']
        },
        'bootstrapQ':{
        	exports : '$',
        	deps : ['jquery', 'bootstrap']
		},
		'ConfirmWindow':{
			exports : '$',
			deps : ['jquery', 'bootstrap','bootstrapQ']
		}
	}
});