export const toLiteralDateTime = (dateTime: Date) => {
  const date = dateTime.toLocaleDateString('fr-FR');
  const time = dateTime.toLocaleTimeString('fr-FR').slice(0, -3);

  return `${date} à ${time}`;
};
