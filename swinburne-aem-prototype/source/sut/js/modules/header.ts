import NavToggler from '@global/modules/header/nav-toggler'
import QuickSearch from '@global/modules/header/quick-search'

import { isAuthorEditMode } from '@global/utilities/aem'

export default async () => {
  // Header navigation toggler
  NavToggler()

  // Quick search toggle
  QuickSearch()

  // Append Font Awesome icons to the navigation links
  if (!isAuthorEditMode()) {
    const { default: Icons } = await import(/* webpackChunkName: "header-icons" */ '@global/modules/header/icons')
    Icons()
  }
}
