export default async () => {
  if ('objectFit' in document.documentElement.style === false) {
    const {
      default: objectFitImages,
    } = await import(/* webpackChunkName: "object-fit-images" */ 'object-fit-images')

    objectFitImages()
  }
}
