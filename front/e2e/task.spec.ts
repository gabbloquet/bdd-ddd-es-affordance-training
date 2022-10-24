import { test, expect } from '@playwright/test';
import { visitHomepage } from './commands';

test('creates tasks', async ({ page }) => {
  visitHomepage(page);

  await page.getByPlaceholder('Ajouter une tâche').fill('Aller à la hutte vider les stocks');

  await expect(page.getByText('Aller à la hutte vider les stocks')).toBeVisible();
});
