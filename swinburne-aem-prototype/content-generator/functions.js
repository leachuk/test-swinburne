/* eslint-disable no-loop-func */
/* eslint-disable no-param-reassign */
const colors      = require('colors')
const fs          = require('fs')
const mkdirp      = require('mkdirp')
const { resolve } = require('path')
const util        = require('util');

const {
  each,
  escape,
  size,
} = require('lodash')

const rootPath = 'content'

const breakpoints = {
  sm: null,
  md: 'Medium',
  lg: 'Large',
  xl: 'Extra Large',
}

const prefixes = {
  acc: 'Align Content Center',
  ace: 'Align Content End',
  acs: 'Align Content Start',

  aic: 'Align Items Center',
  aie: 'Align Items End',
  ais: 'Align Items Start',

  jc: 'Justify Content Center',
  je: 'Justify Content End',
  js: 'Justify Content Start',

  m  : '',
  mt : 'Top',
  mb : 'Bottom',
  ml : 'Left',
  mr : 'Right',
  mx : '(X-Axis)',
  my : '(Y-Axis)',

  p  : '',
  pt : 'Top',
  pb : 'Bottom',
  pl : 'Left',
  pr : 'Right',
  px : '(X-Axis)',
  py : '(Y-Axis)',

  c1: 'C1 (#008e9e)',
  c2: 'C2 (#7fe4e0)',
  c3: 'C3 (#c3e8e7)',
  c4: 'C4 (#e6f4f6)',
  c5: 'C5 (#f1f9fa)',
  c6: 'C6 (#003781)',
  c7: 'C7 (#007ab3)',
  c8: 'C8 (#fdd25c)',
  c9: 'C9 (#ffe8b0)',

  g1: 'G1 (#414141)',
  g2: 'G2 (#5a5360)',
  g3: 'G3 (#635c69)',
  g4: 'G4 (#cad4de)',
  g5: 'G5 (#f2f2f2)',
  g6: 'G6 (#f7f6f7)',
  g7: 'G7 (#fff)',

  // Specific styles
  'col'          : 'Columns',
  'quote-suffix' : 'Quote Suffix',
  'nowrap'       : 'No Wrap'
}

function currentPath(path) {
  return resolve(__dirname, path)
}

function getBreakpointInfix(breakpoint) {
  return breakpoint === 'sm' ? '' : breakpoint
}

function mapBreakpointToName(breakpoint) {
  return breakpoints[breakpoint]
}

let templateCache = {}
function loadTemplateForCategory(category = null) {
  if (!category) {
    throw new Error('Invalid category name supplied!')
  }

  if (templateCache[category]) {
    return templateCache[category]
  }

  templateCache[category] = fs.readFileSync(currentPath(`./templates/${category}.xml`), 'utf-8').toString()
  return templateCache[category]
}

function normalisePrefix(prefix, emptyTitle = false) {
  const newPrefix = prefixes[prefix]

  if (!newPrefix) {
    return !emptyTitle ? titleCase(fixTitle(prefix)) : '';
  }

  return titleCase(fixTitle(newPrefix));
}

function fixTitle(title) {
  if (title.length > 0) {
    return title
      .replace(/-/g, ' ')
      .replace(/\$/g, '')
      .replace(/{/g, '')
      .replace(/}/g, '')
      .replace(/\s\s/g, ' ')
      .replace(/\s\s/g, ' ')
      .trim()
  }

  return title
}

function titleCase(str) {
  try {
    return str.toLowerCase().split(' ').map(word => (
      word.replace(word[0], word[0].toUpperCase())
    )).join(' ')
  } catch (ex) {
    console.log(colors.red(`Error: ${str};`), ex)
  }

  return str
}

function generateContent(categoryTemplate, categoryTemplateDefault, category, path, contentData) {
  let folderName = ''
  let template   = categoryTemplate

  if (contentData.template !== '') {
    template = loadTemplateForCategory(contentData.template)
  }

  // Make a copy of the template
  let templatePatch = template

  if (contentData.type === 'tag') {
    // Replace the template vars
    templatePatch = templatePatch.replace('%%title%%', contentData.title)

    if (contentData.valueFormatted !== '') {
      templatePatch = templatePatch.replace('%%value%%', escape(contentData.valueFormatted))
    } else {
      templatePatch = templatePatch.replace('%%value%%', escape(contentData.prefixValue + contentData.value))
    }

    templatePatch = templatePatch.replace('%%description%%', contentData.description)

    // Strip the folder name if the category is 'component-style-module'
    if (!contentData.flat) {
      folderName = contentData.value

      const prefixToFolders = contentData.prefixToFolders
      if (size(prefixToFolders)) {
        each(prefixToFolders, (replacement, prefix) => {
          if (new RegExp(prefix).test(folderName)) {
            folderName = `${replacement}/${folderName}`
          }
        })
      }
    }
  } else {
    if (contentData.content !== undefined) {
      for (const field of Object.keys(contentData.content)) {
        folderName    = field
        templatePatch = templatePatch.replace('%%node%%', field)

        const name = contentData.content[field]

        for (const fieldvalue of Object.keys(name)) {
          const value = name[fieldvalue]
          templatePatch = templatePatch.replace('%%' + fieldvalue + '%%', value)
        }
      }
    } else {
      console.log('Nothing to do with:', path)
      return
    }
  }

  writeTemplate(`${rootPath}/${category}/${path}/${folderName}`, categoryTemplateDefault, templatePatch)
}

function verifyTemplate(path,content) {
  try {
    libxmljs.parseXml(content)
  } catch (ex) {
    console.log('Invalid Template for ' + path)
    return false
  }

  return true
}

function writeTemplate(outputPath, templateDefault, template) {
  createTemplate(outputPath, template).then(res => {
    consoleResult(...res)

    // Walk up the path and ensure parents have content.xml
    let paths = outputPath.split('/')
    let promises = []

    paths.pop()

    if (paths.length > 0) {
      while (paths.length !== 0) {
        let node = paths[paths.length - 1] || ''
        let title = paths[paths.length - 1] || ''

        title = normalisePrefix(title)

        let templateDefaultPatch = templateDefault
          .replace('%%title%%', title)
          .replace('%%node%%', node)

        promises.push(createTemplate(paths.join('/'), templateDefaultPatch))
        paths.pop()
      }
    }

    Promise.all(promises).then(walks => {
      // console.log('---------------------------------------------------------------------')
      // console.log('Finished walking up the tree!')
      // console.log('---------------------------------------------------------------------')
      walks.forEach(walk => consoleResult(...walk))
    })
  })
}

function createTemplate(outputPath, template) {
  mkdirp.sync(currentPath(outputPath))

  return new Promise(res => {
    if (!fs.existsSync(currentPath(`${outputPath}/.content.xml`))) {
      fs.writeFile(currentPath(`${outputPath}/.content.xml`), template, err => {
        if (err) {
          res([false, outputPath, err])
        } else {
          res([true, outputPath])
        }
      })
    }
  })
}

function consoleResult(success, outputPath, err = null) {
  if (!success) {
    console.log(colors.red('Unable to save content for:'), outputPath, '\n', err)
  } else {
    console.log('Saved content for:', outputPath)
  }
}

function formatSizeWithSuffix(input) {
  let suffix = ''
  if (input > 3 && input < 21) {
    suffix = 'th'
  } else {
    switch (input % 10) {
      case 1:
        suffix = 'st'
        break
      case 2:
        suffix = 'nd'
        break
      case 3:
        suffix = 'rd'
        break
      default:
        suffix = 'th'
    }
  }

  return input + suffix
}

function parseTitle(title, prefix, options = {}) {
  if (title === undefined) {
    return ''
  }

  let breakpointName
  if (options.bp) {
    breakpointName = mapBreakpointToName(options.bp)
  }

  const prefixNormalised = normalisePrefix(prefix, options.emptyTitle)

  title = title
    .replace('%%breakpoint%%', breakpointName ? `- ${breakpointName} Up` : '')
    .replace('%%prefix_normalised%%', prefixNormalised)
    .replace('%%size%%', (options.size !== undefined && options.size.toString()) || '')
    .replace('%%sizeWithSuffix%%', options.size ? formatSizeWithSuffix(options.size) : '')

  if (/^\s+?-/.test(title)) {
    title = title.replace(/^\s+?-/, '')
  }

  return title.trim()
}

module.exports = {
  currentPath,
  formatSizeWithSuffix,
  generateContent,
  getBreakpointInfix,
  loadTemplateForCategory,
  mapBreakpointToName,
  normalisePrefix,
  parseTitle,
}
