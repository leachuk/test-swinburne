import Icons from '@global/modules/header/icons'
import NavToggler from '@global/modules/header/nav-toggler'
import QuickSearch from '@global/modules/header/quick-search'

export default () => {
  // Header navigation toggler
  NavToggler()

  // Quick search toggle
  QuickSearch()

  // Append Font Awesome icons to the navigation links
  Icons()
}
