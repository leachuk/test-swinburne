const longArrowRight = document.createElement('i')
longArrowRight.setAttribute('class', 'fal fa-long-arrow-right')

export default () => {

  // Links
  const links = document.querySelectorAll('.link.btn, .btn.btn-link, header .nav-link')

  if (links.length) {
    for (const link of links) {
      link.appendChild(longArrowRight.cloneNode())
    }
  }

}
