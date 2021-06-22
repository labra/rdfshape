(self.webpackChunkrdfshape_api_webdocs=self.webpackChunkrdfshape_api_webdocs||[]).push([[302],{3905:function(e,t,n){"use strict";n.d(t,{Zo:function(){return s},kt:function(){return d}});var r=n(7294);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function o(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function l(e,t){if(null==e)return{};var n,r,a=function(e,t){if(null==e)return{};var n,r,a={},i=Object.keys(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||(a[n]=e[n]);return a}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(a[n]=e[n])}return a}var p=r.createContext({}),c=function(e){var t=r.useContext(p),n=t;return e&&(n="function"==typeof e?e(t):o(o({},t),e)),n},s=function(e){var t=c(e.components);return r.createElement(p.Provider,{value:t},e.children)},u={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},m=r.forwardRef((function(e,t){var n=e.components,a=e.mdxType,i=e.originalType,p=e.parentName,s=l(e,["components","mdxType","originalType","parentName"]),m=c(n),d=a,h=m["".concat(p,".").concat(d)]||m[d]||u[d]||i;return n?r.createElement(h,o(o({ref:t},s),{},{components:n})):r.createElement(h,o({ref:t},s))}));function d(e,t){var n=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var i=n.length,o=new Array(i);o[0]=m;var l={};for(var p in t)hasOwnProperty.call(t,p)&&(l[p]=t[p]);l.originalType=e,l.mdxType="string"==typeof e?e:a,o[1]=l;for(var c=2;c<i;c++)o[c]=n[c];return r.createElement.apply(null,o)}return r.createElement.apply(null,n)}m.displayName="MDXCreateElement"},7994:function(e,t,n){"use strict";n.r(t),n.d(t,{frontMatter:function(){return l},metadata:function(){return p},toc:function(){return c},default:function(){return u}});var r=n(2122),a=n(9756),i=(n(7294),n(3905)),o=["components"],l={id:"deployment_manual",title:"Running with SBT"},p={unversionedId:"api-deployment/deployment_manual",id:"api-deployment/deployment_manual",isDocsHomePage:!1,title:"Running with SBT",description:"Requirements",source:"@site/../rdfshape-docs/target/mdoc/api-deployment/deployment_manual.md",sourceDirName:"api-deployment",slug:"/api-deployment/deployment_manual",permalink:"/rdfshape-api/docs/api-deployment/deployment_manual",version:"current",frontMatter:{id:"deployment_manual",title:"Running with SBT"},sidebar:"docsSidebar",previous:{title:"Deploying RDFShape API",permalink:"/rdfshape-api/docs/api-deployment/deployment_overview"},next:{title:"Running with Docker",permalink:"/rdfshape-api/docs/api-deployment/deployment_docker"}},c=[{value:"Requirements",id:"requirements",children:[]},{value:"Interactive mode with SBT",id:"interactive-mode-with-sbt",children:[]},{value:"Binary mode",id:"binary-mode",children:[]},{value:"Serving with HTTPS",id:"serving-with-https",children:[]}],s={toc:c};function u(e){var t=e.components,n=(0,a.Z)(e,o);return(0,i.kt)("wrapper",(0,r.Z)({},s,n,{components:t,mdxType:"MDXLayout"}),(0,i.kt)("h2",{id:"requirements"},"Requirements"),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},"RDFShape API requires ",(0,i.kt)("a",{parentName:"li",href:"https://www.scala-sbt.org/"},"SBT")," to be built")),(0,i.kt)("h2",{id:"interactive-mode-with-sbt"},"Interactive mode with SBT"),(0,i.kt)("p",null,"The way to familiarize yourself with the software is to run the ",(0,i.kt)("inlineCode",{parentName:"p"},"sbt")," command, which will open the ",(0,i.kt)("em",{parentName:"p"},"sbt shell"),",\nand execute commands from there."),(0,i.kt)("ol",null,(0,i.kt)("li",{parentName:"ol"},"Clone this repository"),(0,i.kt)("li",{parentName:"ol"},"Go to directory where RDFShape API source code is located and execute ",(0,i.kt)("inlineCode",{parentName:"li"},"sbt"),". After some time downloading dependencies and\ncompiling the source code, the ",(0,i.kt)("em",{parentName:"li"},"sbt shell")," will launch if everything went right."),(0,i.kt)("li",{parentName:"ol"},"From this point, you may execute several commands from the sbt shell:",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("inlineCode",{parentName:"li"},"run")," for the API to launch and be accessible at ",(0,i.kt)("a",{parentName:"li",href:"http://localhost:8080"},"localhost:8080"),"."),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("inlineCode",{parentName:"li"},"run --help")," to see the help menu with further usage\ninformation.")))),(0,i.kt)("h2",{id:"binary-mode"},"Binary mode"),(0,i.kt)("p",null,"The fastest way to run RDFShape API is to compile the code and generate an executable file:"),(0,i.kt)("ol",null,(0,i.kt)("li",{parentName:"ol"},"Clone this repo and run ",(0,i.kt)("inlineCode",{parentName:"li"},"sbt"),", as seen above."),(0,i.kt)("li",{parentName:"ol"},"From the sbt shell, run ",(0,i.kt)("inlineCode",{parentName:"li"},"Universal/packageBin"),"."),(0,i.kt)("li",{parentName:"ol"},"A zip file with an executable and all the program dependencies will be created\ninside ",(0,i.kt)("inlineCode",{parentName:"li"},"(ProjectFolder)/target/universal"),".")),(0,i.kt)("h2",{id:"serving-with-https"},"Serving with HTTPS"),(0,i.kt)("p",null,"You can serve RDFShape API with HTTPS in 2 ways:"),(0,i.kt)("ol",null,(0,i.kt)("li",{parentName:"ol"},(0,i.kt)("strong",{parentName:"li"},"[Recommended]")," Web server setup:",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Run a web server (i.e., Nginx) in your machine or in a separate container and configure it as a reverse proxy that\nforwards incoming requests to the API. Configure your web server to use HTTPS to communicate with clients."),(0,i.kt)("li",{parentName:"ul"},"Launch the application ",(0,i.kt)("strong",{parentName:"li"},"normally")," (no ",(0,i.kt)("inlineCode",{parentName:"li"},"--https")," is required, the web server will handle it)."))),(0,i.kt)("li",{parentName:"ol"},"Manual setup:",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Set the following environment variables in your machine/container, so it can search and use your certificates in\na ",(0,i.kt)("a",{parentName:"li",href:"https://docs.oracle.com/javase/8/docs/api/java/security/KeyStore.html"},"Java keystore"),":",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("inlineCode",{parentName:"li"},"KEYSTORE_PATH"),": location of the keystore storing the certificate."),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("inlineCode",{parentName:"li"},"KEYSTORE_PASSWORD"),": password protecting the keystore (leave empty if there is none)."),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("inlineCode",{parentName:"li"},"KEYMANAGER_PASSWORD"),": password protecting the certificate (leave empty is there is none)."))),(0,i.kt)("li",{parentName:"ul"},"Launch the application with the ",(0,i.kt)("inlineCode",{parentName:"li"},"--https")," argument.")))))}u.isMDXComponent=!0}}]);