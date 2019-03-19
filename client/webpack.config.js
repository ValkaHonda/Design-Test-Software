const webpack = require('webpack')
const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

module.exports = {
    entry: ['./src/index.jsx'],
    output: {
        path: path.resolve(__dirname, 'public/dist'),
        filename: 'app.js'
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './public/index.html',
            filename: 'index.html'
        }),
        new webpack.HotModuleReplacementPlugin(),
        new MiniCssExtractPlugin({
            filename: "styles.css"
        })
    ],
    module: {
        rules: [
            {
                test: /\.(js|jsx)/,
                exclude: /node_modules/,
                loader: 'babel-loader',
                options: {
                    babelrc: true,
                    cacheDirectory: true
                }
            },
            {
                test: /\.(jpe?g|png|gif|svg|ttf|woff2?|eot)(\?.+)?$/i,
                use: [
                    {
                    loader: 'file-loader',
                    options: {
                        name: '[name].[ext]'
                    }
                    }
                ]
            },
            {
                test: /\.(ttf|svg|eot)(\?[a-z0-9#]+)?$/,
                loader: 'file-loader',
                options: {
                    name: 'fonts/[name].[hash].[ext]',
                    outputPath: 'css/'
                }
            },
            {
                test: /\.(s[c|a]ss|css)$/,
                use: ['style-loader', MiniCssExtractPlugin.loader, 'css-loader', 'sass-loader']
            },
        ]
    },
    resolve: {
        extensions: ['.js', '.jsx', '.json'],
        alias: {
            '~~': path.resolve(__dirname, 'src')
        }
    },
    devServer: {
        contentBase: path.join(__dirname, 'public'),
        compress: false,
        port: 3000,
        historyApiFallback: true
    },
    devtool: 'source-map'
}