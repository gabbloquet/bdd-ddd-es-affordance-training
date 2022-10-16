export const toLiteralDateTime = (dateTime: Date): string => {
  const date = new Date(dateTime).toLocaleDateString('fr-FR');
  const time = new Date(dateTime).toLocaleTimeString('fr-FR').slice(0, -3);

  return `${date} à ${time}`;
};
