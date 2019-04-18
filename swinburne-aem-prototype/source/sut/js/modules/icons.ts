export default () => {
  // Links component
  //
  // This applies only to any link components that have the classes: `.link` & `.btn`. We
  // don't need to apply an icon on any other form of a 'button' because they don't fall
  // under this fallback.
  const links = document.querySelectorAll('.link.btn')

  if (links.length) {
    const icon = document.createElement('i')
    icon.setAttribute('class', 'fal fa-long-arrow-right')

    for (const link of links) {
      link.appendChild(icon.cloneNode())
    }
  }
}
