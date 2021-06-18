const deployUrl = "https://weso.github.io"
const baseUrl = "/rdfshape-api/"
const docsUrl = `${deployUrl}${baseUrl}api/es/weso/rdfshape/`

/** @type {import('@docusaurus/types').DocusaurusConfig} */
module.exports = {
  title: "RDFShape API",
  tagline: "Processing and validation of RDF with ShEx, SHACL and more",
  organizationName: "weso", // GitHub org/user name.
  projectName: "rdfshape-api", // Repo name.
  url: deployUrl,
  baseUrl: baseUrl,
  onBrokenLinks: "throw",
  onBrokenMarkdownLinks: "warn",
  favicon: "favicon.ico",
  themeConfig: {
    hideableSidebar: false,
    colorMode: {
      defaultMode: "light",
      disableSwitch: false,
      respectPrefersColorScheme: true,
      switchConfig: {
        darkIcon: "🌙",
        lightIcon: "\u2600",
        darkIconStyle: { marginLeft: "2px" },
        lightIconStyle: { marginLeft: "1px" },
      },
    },
    navbar: {
      title: "RDFShape API",
      logo: {
        alt: "RDFShape API - WESO",
        src: "img/logo-weso.png",
      },
      items: [
        // Scaladoc
        {
          href: docsUrl,
          position: "left",
          label: "Scaladoc",
        },
        // Web pages
        {
          to: "/docs",
          position: "left",
          label: "Web docs",
        },
        // API docs
        {
          href: "https://app.swaggerhub.com/apis/weso/RDFShape",
          position: "left",
          label: "API docs",
        },
        // Link to repo
        {
          href: "https://github.com/weso/rdfshape-api",
          label: "GitHub",
          position: "left",
        },
      ],
    },
    footer: {
      style: "light",
      logo: {
        alt: "RDFShape API - WESO",
        src: "img/logo-weso-footer.png",
        href: "https://www.weso.es/",
      },
      links: [
        {
          title: "About us",
          items: [
            {
              label: "WESO Research Group",
              to: "https://www.weso.es/",
            },
            {
              label: "University of Oviedo",
              to: "https://www.uniovi.es/",
            },
          ],
        },
        {
          title: "Community",
          items: [
            {
              label: "GitHub",
              href: "https://github.com/weso",
            },
            {
              label: "Twitter",
              href: "https://twitter.com/wesoviedo",
            },
          ],
        },
        {
          title: "Further work",
          items: [
            {
              label: "RDFShape project",
              to: "https://github.com/weso/rdfshape",
            },
            {
              label: "More software by WESO",
              href: "https://www.weso.es/#software",
            },
          ],
        },
      ],
      copyright: `Copyright © ${new Date().getFullYear()} WESO Research Group`,
    },
  },
  presets: [
    [
      "@docusaurus/preset-classic",
      {
        docs: {
          path: "../rdfshape-docs/target/mdoc",
          sidebarPath: require.resolve("./sidebars.js"),
          editUrl: "https://github.com/weso/rdfshape-api/edit/master/website/",
        },
        blog: false,
        theme: {
          customCss: require.resolve("./src/css/custom.css"),
        },
        sitemap: {
          trailingSlash: true,
        },
      },
    ],
  ],
};
