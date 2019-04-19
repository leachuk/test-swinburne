import { isAuthorEditMode } from '@global/utilities/aem';

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

  // When in author mode, we need to watch for DOM changes so we can correctly inject the
  // icons needed dynamically.
  if (isAuthorEditMode()) {
    const mutationObserver = new MutationObserver((mutations: MutationRecord[]) => {
      mutations.forEach((mutation: MutationRecord) => {
        const addedNodes = mutation.addedNodes

        if (addedNodes.length) {
          console.log(addedNodes)
        }
      })
    })

    mutationObserver.observe(document.documentElement, {
      childList : true,
      subtree   : true,
    })
  }

}
