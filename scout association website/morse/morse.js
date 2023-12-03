
/* first array containing letters and nbs, scnd array containin morse, compare input1 with array1 and take i,compare input2 and array2 , check if i==i.*/
function showtab(){
    document.getElementById("morsetab").style.display="block";
    document.getElementById("showbutt").style.display="none";
    document.getElementById("hidebutt").style.display="block";   
}
function hidetab(){
    document.getElementById("morsetab").style.display="none";
    document.getElementById("hidebutt").style.display="none";
    document.getElementById("showbutt").style.display="block";
}

  const morseDict = {
    'A': '.-',    'B': '-...',  'C': '-.-.',  'D': '-..',   'E': '.',     'F': '..-.',
    'G': '--.',   'H': '....',  'I': '..',    'J': '.---',  'K': '-.-',   'L': '.-..',
    'M': '--',    'N': '-.',    'O': '---',   'P': '.--.',  'Q': '--.-',  'R': '.-.',
    'S': '...',   'T': '-',     'U': '..-',   'V': '...-',  'W': '.--',   'X': '-..-',
    'Y': '-.--',  'Z': '--..',  '0': '-----', '1': '.----', '2': '..---', '3': '...--',
    '4': '....-', '5': '.....', '6': '-....', '7': '--...', '8': '---..', '9': '----.'
  };
  function checkMorse() {
    
    const character = document.getElementById("character").value.toUpperCase();
    const morseCode = document.getElementById("morse-code").value;
    
    const expectedMorseCode = morseDict[character];
    var gdJob= new Audio('gdJob.mp3');
    var tryAgain= new Audio('tryAgain.mp3');
    
    if (morseCode === expectedMorseCode) {
      document.getElementById("result").innerHTML = "The entered morse code is correct! Good Job :)";
      gdJob.play();
    } else {
      document.getElementById("result").innerHTML = "The entered morse code is incorrect! Try again :(";
      tryAgain.play();
    }
  }
  const morseDict1 = {
    'A': '.-',    'B': '-...',  'C': '-.-.',  'D': '-..',   'E': '.',     'F': '..-.',
    'G': '--.',   'H': '....',  'I': '..',    'J': '.---',  'K': '-.-',   'L': '.-..',
    'M': '--',    'N': '-.',    'O': '---',   'P': '.--.',  'Q': '--.-',  'R': '.-.',
    'S': '...',   'T': '-',     'U': '..-',   'V': '...-',  'W': '.--',   'X': '-..-',
    'Y': '-.--',  'Z': '--..',  '0': '-----', '1': '.----', '2': '..---', '3': '...--',
    '4': '....-', '5': '.....', '6': '-....', '7': '--...', '8': '---..', '9': '----.'
  };
  
  function convertToMorse() {
    
    const word = document.getElementById("word").value.toUpperCase();
    
    
    const characters = word.split('');
    
   
    const morseCodeArray = characters.map((char) => {
      return morseDict1[char] || '';
    });
    
    
    const morseCode = morseCodeArray.join(' ');
    
    
    document.getElementById("result1").textContent = morseCode;

  }