import moment from 'moment';
export function format(date, pattern) {
  if (date) return moment(date).format(pattern);
  return null;
}

export const date_time_pattern = 'YYYY/MM/DD HH:mm';
export const date_time_with_second_pattern = 'YYYY/MM/DD HH:mm:ss';
export const date_pattern = 'YYYY/MM/DD';
export const time_pattern = 'HH:mm:ss';

export function datetime(date) {
  return format(date, date_time_pattern);
}
export function datetimeWithSecond(date) {
  return format(date, date_time_with_second_pattern);
}

export function date(date) {
  return format(date, date_pattern);
}

export function time(date) {
  return format(date, time_pattern);
}

export function duration(secondsData) {
  const duration = moment.duration(secondsData, 'seconds');
  const { days, hours, milliseconds, minutes, months, seconds, years } =
    duration._data;
  const res = [
    [years, '年'],
    [months, '个月'],
    [days, '天'],
    [hours, '小时'],
  ]
    .filter((item) => item[0])
    .map((item) => item[0] + item[1])
    .join('');
  return res ? res : '<1小时';
}
