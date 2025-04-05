import { fileURLToPath, URL } from "node:url";

import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      // 配置别名
      "@": fileURLToPath(new URL("./src", import.meta.url)),
    },
  },
  server: {
    proxy: {
      // 配置代理服务器
      "/api": {
        // 代理规则
        target: "http://localhost:8080", // 目标服务器
        secure: false, // 否验证目标服务器的 SSL 证书。
        changeOrigin: true, // 是否修改请求头中的 Origin 字段。
        rewrite: (path) => path.replace(/^\/api/, ""), //路径重写 匹配以/api为开头的字符串并将这个替换掉在 JS 里，正则表达式通常会被包含在两个斜杠 / 之间  所以如果要匹配实际的 / 字符，就需要使用反斜杠 \ 进行转义 当 ^ 处于正则表达式的起始位置时，它用于匹配字符串的开头。
      },
    },
  },
});
