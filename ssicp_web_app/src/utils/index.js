// utilsPlugin.js

import * as formatter from './formatter.js';
import * as date from './date.js';
import getMime from './mime.js';
import { createLoadingProxy, formValidation, highlight } from './utils.js';

const utilsPlugin = {
  install(app) {
    app.config.globalProperties.$formatter = formatter;
    app.config.globalProperties.$date = date;
    app.config.globalProperties.$utils = {
      getMime,
      createLoadingProxy,
      formValidation,
      highlight,
    };
  },
};

export default utilsPlugin;
