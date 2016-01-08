'use strict';
require('es6-promise').polyfill();
var webpack = require('webpack');
module.exports = {
  module: {
    loaders: [
      { test: /\.js$/, loaders: ['babel-loader'], exclude: /node_modules/ },
      {
        test: /\.css$/,
        loader: "style?sourceMap!css"
      },
      {
        // test: /\.(gif|png|woff|eot|woff2|ttf|svg)$/,
        test: /\.(gif|png)$/,
        loader: 'url-loader?limit=400000'
      },

      // LESS
      {
        test: /\.less$/,
        loader: 'style?sourceMap!css?sourceMap!less?sourceMap'
      }
      ,

      { test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/, loader: "url-loader?limit=400000&minetype=application/font-woff" },
      { test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/, loader: "url-loader?limit=400000" }
    ]
  },
  output: {
    library: 'ls-redux-ui',
    libraryTarget: 'umd'
  },
  plugins: [
    new webpack.ProvidePlugin({
      $: "jquery",
      jQuery: "jquery",
      "window.jQuery": "jquery",
      "root.jQuery": "jquery",
      _:"underscore"
    })//使jquery变成全局变量，不用在自己文件require('jquery')了

  ],
  resolve: {
      extensions: ['', '.js','.jsx']
  }
};
