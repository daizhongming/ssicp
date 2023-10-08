import * as dateUtils from './date.js';

export function datetimeWithSecond() {
  if (arguments[2]) return dateUtils.datetimeWithSecond(arguments[2]);
  return arguments[2];
}
export function datetime() {
  if (arguments[2]) return dateUtils.datetime(arguments[2]);
  return arguments[2];
}
export function date() {
  if (arguments[2]) return dateUtils.date(arguments[2]);
  return arguments[2];
}
export function duration() {
  if (arguments[2]) return dateUtils.duration(arguments[2]);
  return arguments[2];
}

export function dict(list, value) {
  if (!list) return value;
  const item = list.find((item) => item.dictValue == value);
  if (!item) return value;
  return item.dictLabel ? item.dictLabel : value;
}

export function fileSize() {
  let size = arguments[2];
  if (!size) return size;
  const units = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
  const i = parseInt(Math.floor(Math.log(size) / Math.log(1024)));
  return Math.round(size / Math.pow(1024, i), 2) + ' ' + units[i];
}
