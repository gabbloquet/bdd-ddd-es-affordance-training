export const isNull = (object: object): boolean =>
  object && Object.keys(object).length === 0 && Object.getPrototypeOf(object) === Object.prototype;
