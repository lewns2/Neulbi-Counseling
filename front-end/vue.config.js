// Vue3 관련 설정 파일
module.exports = {
  devServer: {
    https: false,
    port: 8083,
    open: true,
    proxy: {
      "/": {
        target: "https://i6e202.p.ssafy.io:8443/",
      },
    },
    historyApiFallback: true,
    hot: true,
  },
  css: {
    requireModuleExtension: false, // import 시에 경로에 .module 포함 안해도 됨.
  },
  transpileDependencies: ["element-plus"],
  lintOnSave: false,
  outputDir: "/app/dist",
};
