(self.webpackChunkrdfshape_api_webdocs=self.webpackChunkrdfshape_api_webdocs||[]).push([[674],{3905:function(e,t,n){"use strict";n.d(t,{Zo:function(){return c},kt:function(){return m}});var r=n(7294);function i(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function a(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function o(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?a(Object(n),!0).forEach((function(t){i(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):a(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function l(e,t){if(null==e)return{};var n,r,i=function(e,t){if(null==e)return{};var n,r,i={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(i[n]=e[n]);return i}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(i[n]=e[n])}return i}var u=r.createContext({}),p=function(e){var t=r.useContext(u),n=t;return e&&(n="function"==typeof e?e(t):o(o({},t),e)),n},c=function(e){var t=p(e.components);return r.createElement(u.Provider,{value:t},e.children)},d={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},s=r.forwardRef((function(e,t){var n=e.components,i=e.mdxType,a=e.originalType,u=e.parentName,c=l(e,["components","mdxType","originalType","parentName"]),s=p(n),m=i,f=s["".concat(u,".").concat(m)]||s[m]||d[m]||a;return n?r.createElement(f,o(o({ref:t},c),{},{components:n})):r.createElement(f,o({ref:t},c))}));function m(e,t){var n=arguments,i=t&&t.mdxType;if("string"==typeof e||i){var a=n.length,o=new Array(a);o[0]=s;var l={};for(var u in t)hasOwnProperty.call(t,u)&&(l[u]=t[u]);l.originalType=e,l.mdxType="string"==typeof e?e:i,o[1]=l;for(var p=2;p<a;p++)o[p]=n[p];return r.createElement.apply(null,o)}return r.createElement.apply(null,n)}s.displayName="MDXCreateElement"},9125:function(e,t,n){"use strict";n.r(t),n.d(t,{frontMatter:function(){return l},metadata:function(){return u},toc:function(){return p},default:function(){return d}});var r=n(2122),i=n(9756),a=(n(7294),n(3905)),o=["components"],l={id:"deployment_docker",title:"Running with Docker"},u={unversionedId:"api-deployment/deployment_docker",id:"api-deployment/deployment_docker",isDocsHomePage:!1,title:"Running with Docker",description:"Requirements",source:"@site/../rdfshape-docs/target/mdoc/api-deployment/deployment_docker.md",sourceDirName:"api-deployment",slug:"/api-deployment/deployment_docker",permalink:"/rdfshape-api/docs/api-deployment/deployment_docker",version:"current",frontMatter:{id:"deployment_docker",title:"Running with Docker"},sidebar:"docsSidebar",previous:{title:"Running with SBT",permalink:"/rdfshape-api/docs/api-deployment/deployment_manual"},next:{title:"Command Line Interface",permalink:"/rdfshape-api/docs/api-usage/usage_cli"}},p=[{value:"Requirements",id:"requirements",children:[]},{value:"Building the image",id:"building-the-image",children:[]},{value:"Running containers",id:"running-containers",children:[]},{value:"Supported tags",id:"supported-tags",children:[]},{value:"Serving with HTTPS",id:"serving-with-https",children:[]}],c={toc:p};function d(e){var t=e.components,n=(0,i.Z)(e,o);return(0,a.kt)("wrapper",(0,r.Z)({},c,n,{components:t,mdxType:"MDXLayout"}),(0,a.kt)("h2",{id:"requirements"},"Requirements"),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},"Docker must be installed in the machine that will build the images and/or run the containers.")),(0,a.kt)("h2",{id:"building-the-image"},"Building the image"),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},"Pull from our ",(0,a.kt)("a",{parentName:"li",href:"https://github.com/orgs/weso/packages/container/package/rdfshape-api"},"container registry")," or... "),(0,a.kt)("li",{parentName:"ul"},"Use the provided Dockerfile to build RDFShape API images: ",(0,a.kt)("ol",{parentName:"li"},(0,a.kt)("li",{parentName:"ol"},"Run ",(0,a.kt)("inlineCode",{parentName:"li"},"docker build -t {YOUR_IMAGE_NAME} .")," from the project folder.",(0,a.kt)("blockquote",{parentName:"li"},(0,a.kt)("p",{parentName:"blockquote"},"No build arguments are required.")))))),(0,a.kt)("h2",{id:"running-containers"},"Running containers"),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},"When running a container, you may provide the following environment variables via ",(0,a.kt)("inlineCode",{parentName:"li"},"--env"),":",(0,a.kt)("ul",{parentName:"li"},(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("inlineCode",{parentName:"li"},"PORT")," ","[optional]",":",(0,a.kt)("ul",{parentName:"li"},(0,a.kt)("li",{parentName:"ul"},"Port where the API is exposed inside the container. Default is 8080."))),(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("inlineCode",{parentName:"li"},"USE_HTTPS")," ","[optional]",":",(0,a.kt)("ul",{parentName:"li"},(0,a.kt)("li",{parentName:"ul"},"Any non-empty value to try to serve via HTTPS, leave undefined for HTTP.")))))),(0,a.kt)("h2",{id:"supported-tags"},"Supported tags"),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},(0,a.kt)("em",{parentName:"li"},":stable"),": Stable build updated manually."),(0,a.kt)("li",{parentName:"ul"},"<",(0,a.kt)("em",{parentName:"li"},":hashed_tags"),">: Automated builds by our CI pipeline. With the latest features uploaded to our repository but lacking\ninternal testing.")),(0,a.kt)("h2",{id:"serving-with-https"},"Serving with HTTPS"),(0,a.kt)("p",null,"Follow the indications above."))}d.isMDXComponent=!0}}]);