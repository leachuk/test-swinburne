export default () => {
  const navLinks = document.querySelectorAll('.navlist .nav-link')

  if (!navLinks.length) {
    return
  }

  const icon = document.createElement('i')
  icon.setAttribute('class', 'fal fa-long-arrow-right')

  for (const navLink of navLinks) {
    navLink.appendChild(icon.cloneNode())
  }
}
