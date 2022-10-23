import { test, expect } from '@playwright/test';

test('homepage has ultimate title', async ({ page }) => {
  await page.goto('http://localhost:5173/');

  await expect(page).toHaveTitle('Ultimate todolist');

  await expect(page.locator('h1')).toHaveText('Welcome to the ULTIMATE Todolist! 🚀');
});
