(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-892e8c2e"],{b866:function(e,t,n){},f210:function(e,t,n){"use strict";n.r(t);var i=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticStyle:{margin:"20px"}},[n("div",{staticStyle:{"text-align":"right"}},[n("el-button",{staticStyle:{"margin-bottom":"10px","text-align":"right"},attrs:{type:"info",size:"mini",icon:"el-icon-lollipop"},on:{click:e.showFaq}},[e._v("参考字段 ")])],1),n("faq",{ref:"faq_view"}),n("el-form",{attrs:{model:e.form,size:"mini"}},[n("el-form-item",{attrs:{label:"名称：","label-width":e.formLabelWidth}},[n("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.templateName,callback:function(t){e.$set(e.form,"templateName",t)},expression:"form.templateName"}})],1),n("el-form-item",{attrs:{label:"模板数据：","label-width":e.formLabelWidth}},[n("div",{staticStyle:{border:"1px solid #eee"}},[n("codemirror",{ref:"codemirror",staticClass:"code-edit",attrs:{value:e.code.mapperXml,options:e.cmOptions},model:{value:e.form.body,callback:function(t){e.$set(e.form,"body",t)},expression:"form.body"}})],1)])],1),n("div",{staticStyle:{"text-align":"right","margin-top":"10px"}},[n("el-button",{attrs:{type:"success",size:"mini"},on:{click:e.save}},[e._v("保存")]),n("el-button",{attrs:{type:"warning",size:"mini"},on:{click:e.cancel}},[e._v("取消")])],1)],1)},a=[],r=n("5530"),o=n("8f94"),l=n("c621"),s=(n("a7be"),n("b866"),n("fe46"),n("2f62")),c=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("el-dialog",{staticStyle:{"min-height":"400px"},attrs:{title:"变量列表",visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[n("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[n("el-tab-pane",{attrs:{label:"系统通用",name:"first"}},[n("main-desc")],1),n("el-tab-pane",{attrs:{label:"工程配置",name:"second"}},[n("main-desc")],1),n("el-tab-pane",{attrs:{label:"实体级",name:"third"}},[n("main-desc")],1),n("el-tab-pane",{attrs:{label:"字段级",name:"fourth"}},[n("main-desc")],1),n("el-tab-pane",{attrs:{label:"自定义",name:"five"}})],1)],1)},u=[],m=(n("b0c0"),function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("el-descriptions",{staticClass:"margin-top",attrs:{column:1,size:"small",border:""}},e._l(e.data,(function(t){return n("el-descriptions-item",{key:t.name},[n("template",{slot:"label"},[e._v(e._s(t.name))]),e._v(" "+e._s(t.desc)+" ")],2)})),1)}),f=[],d={data:function(){return{data:[{name:"test",desc:"测试数据"}]}}},p=d,b=n("2877"),h=Object(b["a"])(p,m,f,!1,null,"61427181",null),k=h.exports,v={components:{MainDesc:k},data:function(){return{size:"",dialogFormVisible:!1,activeName:"first"}},methods:{openForm:function(){this.dialogFormVisible=!0},handleClick:function(e,t){console.log(e.name)}}},g=v,y=Object(b["a"])(g,c,u,!1,null,null,null),$=y.exports,w={components:{codemirror:o["codemirror"],Faq:$},data:function(){return{dialogFormVisible:!1,form:{groupId:"",templateName:"",body:""},formLabelWidth:"120px",code:{mapperXml:""},cmOptions:{mode:"velocity",theme:"idea",indentUnit:4,smartIndent:!0,tabSize:4,lineNumbers:!0,firstLineNumber:1,showCursorWhenSelecting:!0,readOnly:!1}}},computed:Object(r["a"])({codemirror:function(){return this.$refs.codemirror.codemirror}},Object(s["c"])({currentNode:"template/currentNode"})),methods:{query:function(){var e=this;Object(l["d"])().then((function(t){e.$router.push({path:"/common_setting/template"})}))},save:function(){var e=this;this.form.groupId=this.currentNode.currentId,Object(l["e"])(this.form).then((function(t){e.$confirm("修改成功，是否返回列表?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"success"}).then((function(){e.$router.push({path:"/common_setting/template"})})).catch((function(){}))}))},cancel:function(){this.$router.push({path:"/common_setting/template"})},showFaq:function(){this.$refs.faq_view.openForm()}}},x=w,W=Object(b["a"])(x,i,a,!1,null,null,null);t["default"]=W.exports},fe46:function(e,t,n){(function(e){e(n("56b3"))})((function(e){"use strict";e.defineMode("velocity",(function(){function e(e){for(var t={},n=e.split(" "),i=0;i<n.length;++i)t[n[i]]=!0;return t}var t=e("#end #else #break #stop #[[ #]] #{end} #{else} #{break} #{stop}"),n=e("#if #elseif #foreach #set #include #parse #macro #define #evaluate #{if} #{elseif} #{foreach} #{set} #{include} #{parse} #{macro} #{define} #{evaluate}"),i=e("$foreach.count $foreach.hasNext $foreach.first $foreach.last $foreach.topmost $foreach.parent.count $foreach.parent.hasNext $foreach.parent.first $foreach.parent.last $foreach.parent $velocityCount $!bodyContent $bodyContent"),a=/[+\-*&%=<>!?:\/|]/;function r(e,t,n){return t.tokenize=n,n(e,t)}function o(e,o){var u=o.beforeParams;o.beforeParams=!1;var m=e.next();if("'"==m&&!o.inString&&o.inParams)return o.lastTokenWasBuiltin=!1,r(e,o,l(m));if('"'!=m){if(/[\[\]{}\(\),;\.]/.test(m))return"("==m&&u?o.inParams=!0:")"==m&&(o.inParams=!1,o.lastTokenWasBuiltin=!0),null;if(/\d/.test(m))return o.lastTokenWasBuiltin=!1,e.eatWhile(/[\w\.]/),"number";if("#"==m&&e.eat("*"))return o.lastTokenWasBuiltin=!1,r(e,o,s);if("#"==m&&e.match(/ *\[ *\[/))return o.lastTokenWasBuiltin=!1,r(e,o,c);if("#"==m&&e.eat("#"))return o.lastTokenWasBuiltin=!1,e.skipToEnd(),"comment";if("$"==m)return e.eat("!"),e.eatWhile(/[\w\d\$_\.{}-]/),i&&i.propertyIsEnumerable(e.current())?"keyword":(o.lastTokenWasBuiltin=!0,o.beforeParams=!0,"builtin");if(a.test(m))return o.lastTokenWasBuiltin=!1,e.eatWhile(a),"operator";e.eatWhile(/[\w\$_{}@]/);var f=e.current();return t&&t.propertyIsEnumerable(f)?"keyword":n&&n.propertyIsEnumerable(f)||e.current().match(/^#@?[a-z0-9_]+ *$/i)&&"("==e.peek()&&(!n||!n.propertyIsEnumerable(f.toLowerCase()))?(o.beforeParams=!0,o.lastTokenWasBuiltin=!1,"keyword"):o.inString?(o.lastTokenWasBuiltin=!1,"string"):e.pos>f.length&&"."==e.string.charAt(e.pos-f.length-1)&&o.lastTokenWasBuiltin?"builtin":(o.lastTokenWasBuiltin=!1,null)}return o.lastTokenWasBuiltin=!1,o.inString?(o.inString=!1,"string"):o.inParams?r(e,o,l(m)):void 0}function l(e){return function(t,n){var i,a=!1,r=!1;while(null!=(i=t.next())){if(i==e&&!a){r=!0;break}if('"'==e&&"$"==t.peek()&&!a){n.inString=!0,r=!0;break}a=!a&&"\\"==i}return r&&(n.tokenize=o),"string"}}function s(e,t){var n,i=!1;while(n=e.next()){if("#"==n&&i){t.tokenize=o;break}i="*"==n}return"comment"}function c(e,t){var n,i=0;while(n=e.next()){if("#"==n&&2==i){t.tokenize=o;break}"]"==n?i++:" "!=n&&(i=0)}return"meta"}return{startState:function(){return{tokenize:o,beforeParams:!1,inParams:!1,inString:!1,lastTokenWasBuiltin:!1}},token:function(e,t){return e.eatSpace()?null:t.tokenize(e,t)},blockCommentStart:"#*",blockCommentEnd:"*#",lineComment:"##",fold:"velocity"}})),e.defineMIME("text/velocity","velocity")}))}}]);