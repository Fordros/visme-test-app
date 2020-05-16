module.exports = {
  devServer: {
    port: 3000,
    proxy: {
      '^/api': {
        target: 'http://localhost:9000',
        secure: false,
        changeOrigin: true
      }
    }
  }
}
