import { test, expect } from '@playwright/test';
import { visitHomepage } from './commands';

test('homepage has ultimate title', async ({ page }) => {
  visitHomepage(page);

  await expect(page).toHaveTitle('Ultimate todolist');

  await expect(page.locator('h1')).toHaveText('Welcome to the ULTIMATE Todolist! 🚀');
});
