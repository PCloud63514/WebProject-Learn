module.exports = {
  presets: ['module:metro-react-native-babel-preset'],
  "plugins": [
    '@babel/plugin-transform-flow-strip-types',
    "babel-plugin-transform-typescript-metadata",
    ["@babel/plugin-proposal-decorators", { "legacy": true}],
    ["@babel/plugin-proposal-class-properties", { "loose": true}],
    ["babel-plugin-inline-import", { "extensions": [".svg"] }],
    [
      "module-resolver",
      {
        root: ['./src'],
        extensions: [
          '.ios.ts',
          '.android.ts',
          '.ts',
          '.ios.tsx',
          '.android.tsx',
          '.tsx',
          '.jsx',
          '.js',
          '.json'],
        alias: {
          '@':'./src',
          '@components':'./src/components',
          '@decorator':'./src/decorator'
        }
      }
    ]
  ],
  
  // "presets": [
  //   "@babel/preset-typescript"
  // ]
};