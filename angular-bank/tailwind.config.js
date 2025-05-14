/** @type {import('tailwindcss').Config} */
const twColors = require("tailwindcss/colors");
import primeui from 'tailwindcss-primeui'
module.exports = {
  content: ["./src/**/*.html,ts"],
  theme :{
    colors:{
      ...twColors
    },
    extend:{}
  },
  plugins: [primeui],
};
