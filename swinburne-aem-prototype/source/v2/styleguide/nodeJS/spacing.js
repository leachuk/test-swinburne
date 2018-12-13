let fs = require('fs');

let table = `<table class="styleguide-table"><tr class="styleguide-table__row styleguide-table__row--header"><th class="styleguide-table__header styleguide-table__header--token">Token</th><th class="styleguide-table__header">rem</th><th>px</th></tr>`;

let data = [
  { name: "$spacing-2xs", rem: "0.5", px: "8px" },
  { name: "$spacing-xs", rem: "0.75", px: "12px" },
  { name: "$spacing-sm", rem: "1", px: "16px" },
  { name: "$spacing-md", rem: "1.5", px: "24px" },
  { name: "$spacing-lg", rem: "2", px: "32px" },
  { name: "$spacing-xl", rem: "2.25", px: "36px" },
  { name: "$spacing-2xl", rem: "3", px: "48px" },
  { name: "$spacing-3xl", rem: "4", px: "64px" }
];

data.map( (row) => {
  table += `<tr class="styleguide-table__row">`;
  Object.entries(row).forEach((cell, index) => {
    const cellClass = index === 0 ? 'styleguide-table__cell--code' : '';
    table += `<td class="styleguide-table__cell ${cellClass}">${cell[1]}</td>`;
  });
  table += `</tr>`;
} );

table += `</table>`;

fs.writeFile ("../html/spacing.html", table, function(err) {
    if (err) throw err;
    console.log('complete');
  }
);

