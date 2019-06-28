module.exports = {
  // publicPath: './',
  configureWebpack: {},
  chainWebpack: config => {
    config.module
      .rule('vue')
      .use('vue-svg-inline-loader')
      .loader('vue-svg-inline-loader');
  },


  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8090',
        ws: true,
        changeOrigin: true
      }
    }
  },
  outputDir: 'target/dist',
  assetsDir: 'static'
};
