export const colors = [
    "#f990a7",
    "#3cb371",
    "#0000FF",
    "#9966FF",
    "#aad2ed",
    "#FF00FF",
    "#4C4CFF",
    "#00FFFF",
    "Blue",
    "Red",
    "Green",
    "Yellow",
    "DarkBlue",
    "#3cb371",
    "#0000FF",
    "#9966FF",
    "#f990a7",
    "#aad2ed",
    "#FF00FF",
    "#4C4CFF",
    "#00FFFF",
    "Blue",
    "Red",
    "Green",
    "Yellow",
    "DarkBlue"
];

export function arrayRotate(arr, count) {
    count -= arr.length * Math.floor(count / arr.length);
    arr.push.apply(arr, arr.splice(0, count));
    return arr;
}