(this["webpackJsonpcreate-react-app-antd"]=this["webpackJsonpcreate-react-app-antd"]||[]).push([[0],{118:function(e,t,a){e.exports=a(271)},123:function(e,t,a){},133:function(e,t,a){},271:function(e,t,a){"use strict";a.r(t);var n=a(0),r=a.n(n),c=a(15),l=a.n(c),o=(a(123),a(84),a(40)),s=(a(125),a(114)),i=(a(127),a(83)),m=a(102),u=a(103),p=a(115),d=a(104),h=a(116),g=(a(129),a(8)),E=(a(270),a(58)),f=(a(133),E.a.Item),b=r.a.createElement("div",null,"Copyright 2018 ",r.a.createElement(g.a,{type:"copyright"}),"\u51ef\u5353\u5929\u5de5"),y=function(e){function t(){var e,a;Object(m.a)(this,t);for(var n=arguments.length,r=new Array(n),c=0;c<n;c++)r[c]=arguments[c];return(a=Object(p.a)(this,(e=Object(d.a)(t)).call.apply(e,[this].concat(r)))).state={submitting:!1,autoLogin:!1},a.handleSubmit=function(e){a.props.form.validateFields((function(t,a){t&&e.preventDefault()}))},a.changeAutoLogin=function(e){a.setState({autoLogin:e.target.checked})},a}return Object(h.a)(t,e),Object(u.a)(t,[{key:"render",value:function(){var e=this.state,t=e.autoLogin,a=e.submitting,n=this.props.form.getFieldDecorator;return r.a.createElement("div",{className:"container"},r.a.createElement("div",{className:"content"},r.a.createElement("div",{className:"header"},r.a.createElement("div",null,r.a.createElement("span",{className:"title"},"\u51ef\u5353\u5929\u5de5\u767b\u5f55\u754c\u9762"))),r.a.createElement("div",{className:"desc"},"\u6211\u4eec\u7684\u5f81\u9014\u662f\u661f\u8fb0\u5927\u6d77"),r.a.createElement(E.a,{onSubmit:this.handleSubmit,action:"./oauth/custom/token",method:"POST",className:"login-form"},r.a.createElement(f,null,n("username",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u7528\u6237\u540d!"}]})(r.a.createElement(i.a,{name:"username",prefix:r.a.createElement(g.a,{type:"user",style:{color:"rgba(0,0,0,.25)"}}),placeholder:"admin"}))),r.a.createElement(f,null,n("password",{rules:[{required:!0,message:"Please input your Password!"}]})(r.a.createElement(i.a,{name:"password",prefix:r.a.createElement(g.a,{type:"lock",style:{color:"rgba(0,0,0,.25)"}}),type:"password",placeholder:"admin"}))),r.a.createElement(f,{className:"login-form-forgot"},r.a.createElement(s.a,{checked:t,onChange:this.changeAutoLogin},"\u8bb0\u4f4f\u6211")),r.a.createElement(f,null,r.a.createElement(o.a,{type:"primary",htmlType:"submit",loading:a,className:"login-form-button"},"\u767b\u5f55")))),r.a.createElement("div",{className:"foot"}," ",b," "))}}]),t}(r.a.Component),v=E.a.create()(y);l.a.render(r.a.createElement(v,null),document.getElementById("root"))}},[[118,1,2]]]);
//# sourceMappingURL=main.73840314.chunk.js.map