(self.webpackChunkrdfshape_api_webdocs=self.webpackChunkrdfshape_api_webdocs||[]).push([[527],{3905:function(e,t,a){"use strict";a.d(t,{Zo:function(){return u},kt:function(){return h}});var r=a(7294);function n(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function i(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,r)}return a}function o(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?i(Object(a),!0).forEach((function(t){n(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):i(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function s(e,t){if(null==e)return{};var a,r,n=function(e,t){if(null==e)return{};var a,r,n={},i=Object.keys(e);for(r=0;r<i.length;r++)a=i[r],t.indexOf(a)>=0||(n[a]=e[a]);return n}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)a=i[r],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(n[a]=e[a])}return n}var p=r.createContext({}),c=function(e){var t=r.useContext(p),a=t;return e&&(a="function"==typeof e?e(t):o(o({},t),e)),a},u=function(e){var t=c(e.components);return r.createElement(p.Provider,{value:t},e.children)},d={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},l=r.forwardRef((function(e,t){var a=e.components,n=e.mdxType,i=e.originalType,p=e.parentName,u=s(e,["components","mdxType","originalType","parentName"]),l=c(a),h=n,f=l["".concat(p,".").concat(h)]||l[h]||d[h]||i;return a?r.createElement(f,o(o({ref:t},u),{},{components:a})):r.createElement(f,o({ref:t},u))}));function h(e,t){var a=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var i=a.length,o=new Array(i);o[0]=l;var s={};for(var p in t)hasOwnProperty.call(t,p)&&(s[p]=t[p]);s.originalType=e,s.mdxType="string"==typeof e?e:n,o[1]=s;for(var c=2;c<i;c++)o[c]=a[c];return r.createElement.apply(null,o)}return r.createElement.apply(null,a)}l.displayName="MDXCreateElement"},4833:function(e,t,a){"use strict";a.r(t),a.d(t,{frontMatter:function(){return s},metadata:function(){return p},toc:function(){return c},default:function(){return d}});var r=a(2122),n=a(9756),i=(a(7294),a(3905)),o=["components"],s={id:"webpage_info",title:"About this webpage"},p={unversionedId:"webpage/webpage_info",id:"webpage/webpage_info",isDocsHomePage:!1,title:"About this webpage",description:"The website for RDFShape API is currently hosted in https://www.weso.es/rdfshape-api/. It is a React webpage",source:"@site/../rdfshape-docs/target/mdoc/webpage/webpage_info.md",sourceDirName:"webpage",slug:"/webpage/webpage_info",permalink:"/rdfshape-api/docs/webpage/webpage_info",version:"current",frontMatter:{id:"webpage_info",title:"About this webpage"},sidebar:"docsSidebar",previous:{title:"Logging system",permalink:"/rdfshape-api/docs/api-testing-auditing/testing-auditing_logs"}},c=[{value:"Website edition",id:"website-edition",children:[]},{value:"Website creation guidelines",id:"website-creation-guidelines",children:[{value:"Web pages",id:"web-pages",children:[]},{value:"Web docs",id:"web-docs",children:[]}]},{value:"Issues while creating the webpage",id:"issues-while-creating-the-webpage",children:[]}],u={toc:c};function d(e){var t=e.components,a=(0,n.Z)(e,o);return(0,i.kt)("wrapper",(0,r.Z)({},u,a,{components:t,mdxType:"MDXLayout"}),(0,i.kt)("p",null,"The website for RDFShape API is currently hosted in ",(0,i.kt)("a",{parentName:"p",href:"https://www.weso.es/rdfshape-api/"},"https://www.weso.es/rdfshape-api/"),". It is a ",(0,i.kt)("a",{parentName:"p",href:"https://reactjs.org/"},"React")," webpage\nautomatically generated by ",(0,i.kt)("a",{parentName:"p",href:"https://docusaurus.io/"},"Docusaurus"),"."),(0,i.kt)("p",null,"Pushes to the main branch trigger an automatic re-build and re-publish of the page with the latest changes (if any).\nThis is done with the\nfollowing ",(0,i.kt)("a",{parentName:"p",href:"https://github.com/weso/rdfshape-api/blob/master/.github/workflows/publish_gh_pages.yml"},"GitHub action"),"."),(0,i.kt)("h2",{id:"website-edition"},"Website edition"),(0,i.kt)("p",null,"The website contents are located:"),(0,i.kt)("ol",null,(0,i.kt)("li",{parentName:"ol"},"Inside the ",(0,i.kt)("em",{parentName:"li"},(0,i.kt)("a",{parentName:"em",href:"https://github.com/weso/rdfshape-api/tree/master/website"},"website")," folder:")," Docusaurus\nconfiguration files, React pages, header/footer/sidebar contents, etc."),(0,i.kt)("li",{parentName:"ol"},"Inside the ",(0,i.kt)("em",{parentName:"li"},(0,i.kt)("a",{parentName:"em",href:"https://github.com/weso/rdfshape-api/tree/master/docs"},"docs")," folder:")," Markdown files, first\nprocessed by ",(0,i.kt)("a",{parentName:"li",href:"https://scalameta.org/mdoc/"},"mdoc")," and eventually by Docusaurus to create\nthe ",(0,i.kt)("a",{parentName:"li",href:"https://www.weso.es/rdfshape-api/docs/"},"Web Docs"),".")),(0,i.kt)("h2",{id:"website-creation-guidelines"},"Website creation guidelines"),(0,i.kt)("h3",{id:"web-pages"},"Web pages"),(0,i.kt)("p",null,"In order to create new pages, create a JS file inside ",(0,i.kt)("em",{parentName:"p"},"website/src/pages")," for Docusaurus to be aware of its existence\nand assign them a URL based on their location inside the ",(0,i.kt)("em",{parentName:"p"},"pages")," folder."),(0,i.kt)("h3",{id:"web-docs"},"Web docs"),(0,i.kt)("p",null,"Create new pages using markdown syntax inside the ",(0,i.kt)("em",{parentName:"p"},"docs")," folder. These pages will be processed by ",(0,i.kt)("em",{parentName:"p"},"mdoc")," and assigned a\nURL inside ",(0,i.kt)("inlineCode",{parentName:"p"},"/docs")," by ",(0,i.kt)("em",{parentName:"p"},"docusaurus")," when running the task ",(0,i.kt)("inlineCode",{parentName:"p"},"docs/docusaurusCreateSite")," from SBT."),(0,i.kt)("h2",{id:"issues-while-creating-the-webpage"},"Issues while creating the webpage"),(0,i.kt)("p",null,"In order to use ",(0,i.kt)("em",{parentName:"p"},"mdoc")," in combination with ",(0,i.kt)("em",{parentName:"p"},"docusaurus"),", this ",(0,i.kt)("a",{parentName:"p",href:"https://scalameta.org/mdoc/docs/docusaurus.html"},"guide"),"\nwas followed. However, minor issues occurred:"),(0,i.kt)("ol",null,(0,i.kt)("li",{parentName:"ol"},"The ",(0,i.kt)("em",{parentName:"li"},"package.json")," had to be modified to include the script ",(0,i.kt)("inlineCode",{parentName:"li"},"publish-gh-pages"),"."),(0,i.kt)("li",{parentName:"ol"},"The ",(0,i.kt)("a",{parentName:"li",href:"https://github.com/weso/rdfshape-api/blob/master/website/docusaurus.config.js"},"Docusaurus config file")," had to be\nmodified to indicate what the location of t2.\nThe ",(0,i.kt)("a",{parentName:"li",href:"https://github.com/weso/rdfshape-api/blob/master/website/docusaurus.config.js"},"Docusaurus config file")," had to be\nmodified to indicate what the location of the markdown files with the web docs is.he markdown files with the web docs\nis."),(0,i.kt)("li",{parentName:"ol"},"The ",(0,i.kt)("a",{parentName:"li",href:"https://github.com/weso/rdfshape-api/blob/master/website/sidebars.js"},"sidebar configuration file")," was modified\nto customize the sidebar that is used as navigation when browsing the web docs.")))}d.isMDXComponent=!0}}]);