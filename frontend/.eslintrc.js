module.exports = {
  root: true,
  env: {
    browser: true,
    node: true,
  },
  extends: ['plugin:vue/essential', 'eslint:recommended'],
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    indent: ['warn', 2, { SwitchCase: 1 }],
    'max-len': ['warn', { code: 150 }],
    quotes: ['warn', 'single'],
    // 'implicit-arrow-linebreak': ['warn', 'beside'],
    'comma-dangle': ['warn', 'always-multiline'],

    // vue specific
    'vue/script-indent': ['warn', 2, { baseIndent: 0 }],
    'vue/order-in-components': ['warn'],
  },
  overrides: [
    {
      files: ['*.vue'],
      rules: {
        indent: 'off',
      },
    },
  ],
  parserOptions: {
    parser: 'babel-eslint',
  },
};
