export default () => {
  const promotedCards : Array<any> = [...document.querySelectorAll('[class*="card--promoted-"]')];

  if (promotedCards.length) {
    let promotedCard : any = promotedCards[0];
    let style : string = promotedCard.classList.value.split(" ").pop().split("-").pop();
    promotedCard.classList.add(`${style}-style`);
  }
}
