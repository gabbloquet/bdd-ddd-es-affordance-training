import CommandHelper from './index';
//
// describe('eventHelper', () => {
//   describe('on', () => {
//     it('should add event listener', () => {
//       jest.spyOn(document, 'addEventListener');
//       const listenerMock = jest.fn();
//
//       CommandHelper.on('event', listenerMock);
//
//       expect(document.addEventListener).toHaveBeenCalledWith('event', listenerMock);
//     });
//   });
//
//   describe('off', () => {
//     it('should remove event listener', () => {
//       jest.spyOn(document, 'removeEventListener');
//       const listenerMock = jest.fn();
//
//       CommandHelper.off('event', listenerMock);
//
//       expect(document.removeEventListener).toHaveBeenCalledWith('event', listenerMock);
//     });
//   });
//
//   describe('trigger', () => {
//     it('should dispatch event', () => {
//       jest
//         .spyOn(global, 'CustomEvent')
//         .mockImplementation((type: string, data?: any) => ({ type, ...data }));
//       jest.spyOn(document, 'dispatchEvent').mockImplementation();
//
//       CommandHelper.trigger('event', { title: 'event example' });
//
//       expect(document.dispatchEvent).toHaveBeenCalledWith({
//         detail: { title: 'event example' },
//         type: 'event'
//       });
//     });
//   });
// });
