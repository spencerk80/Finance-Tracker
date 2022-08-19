/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx,html}", "./public/index.html"],
  theme: {
    colors: {
      white: "#ffffff",
      "gray-lightest": "#f8f8fa",
      "gray-lighter": "#e5e5e6",
      "gray-light": "#d1d1d2",
      gray: "#bebebf",
      "gray-dark": "#969698",
      "gray-darker": "#6f6f70",
      "gray-darkest": "#484849",
      black: "#212122",

      "brand-light": "#d3d7f5",
      brand: "#2b68d2",
      "brand-dark": "#233464",

      "cta-light": "#fdd1c6",
      cta: "#d2422b",
      "cta-dark": "#66261a",

      "info-light": "#d9f0fd",
      info: "#3ac5f6",
      "info-dark": "#295e73",

      "warning-light": "#fcf0d4",
      warning: "#e5c355",
      "warning-dark": "#6d5d2d",

      "success-light": "#d9f5d9",
      success: "#53d26b",
      "success-dark": "#2f6437",

      "danger-light": "#ffd4d5",
      danger: "#e64561",
      "danger-dark": "#6e2931",
    },
    extend: {},
  },
  plugins: [],
};
