module.exports = {
  mode: 'jit',
  purge: {
  // in prod look at shadow-cljs output file in dev look at runtime, which will change files that are actually compiled; postcss watch should be a whole lot faster
    content: process.env.NODE_ENV == 'production' ? ["public/js/main.js"] : ["public/js/cljs-runtime/*.js"]
    },
  darkMode: 'media', //  false, 'media' or 'class'
  theme: {
    extend: {},
  },
  variants: {
    extend: {},
  },
  plugins: [
    require('@tailwindcss/forms'),
  ],
  }
