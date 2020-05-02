

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.swing.JOptionPane;

public class Encryption_Decryption {


public static void main(String[] args) throws FileNotFoundException, IOException {
char[] alphabet = ("D?/.,{}[]KLMUVWlmnopXYZEFPQRSGHIJabcdefghABCijkqrstz1234567NOT890 !@#$%^&*()_-uvwxy=+':~`|").toCharArray();
int num[] = new int[alphabet.length];
char[] codes = new char[alphabet.length];
String choices[] = new String[]{"Encrypt and save a new File", "Decrypt an existing File"};
String input = null;
int choice = JOptionPane.showOptionDialog(null, "Please Select an option", "Function", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, choices);
if (choice == 0) {
String[] saveOptions = new String[]{"File 1", "File 2", "File 3"};
int schoice = JOptionPane.showOptionDialog(null, "Please Select a saving option", "saving", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, saveOptions, saveOptions);
input = JOptionPane.showInputDialog(null, "Enter what you want to encrypt");
char[] inputletters = input.toCharArray();
boolean DL[] = new boolean[inputletters.length];
GNC(num, alphabet);
startup(num, alphabet, codes);
encrypt(codes, alphabet, DL, num, input, inputletters, schoice);

} else if (choice == 1) {
String[] saveOptions = new String[]{"File 1", "File 2", "File 3"};
int schoice = JOptionPane.showOptionDialog(null, "Please Select a loading option", "Loading", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, saveOptions, saveOptions);

switch (schoice) {
case 0:
load1(input, alphabet, codes, num);
break;
case 1:
load2(input, alphabet, codes, num);
break;
case 2:
load3(input, alphabet, codes, num);
break;
default:
break;
}

}

//        for (int t = 0; t < num.length; t++) {
//        System.out.println(alphabet[t] + " = " + codes[t]);
//        }

}

public static void encrypt(char[] codes, char[] alphabet, boolean[] DL, int[] num, String input, char[] inputletters, int schoice) throws IOException {

char[] encryptedletters = new char[inputletters.length];
String encryptedword;
int counter = 0;
int q = 1;
int j;
int h = 0;
int random = 0;
int prandom = 0;
boolean stop = false;
for (int z = 0; z < inputletters.length; z++) {
for (int p = 0; p < codes.length; p++) {
if (inputletters[z] == alphabet[p]) {
encryptedletters[z] = codes[p];
}
}

}
DL = new boolean[encryptedletters.length];
for (int r = 0; r < encryptedletters.length; r++) {
DL[r] = false;
}

for (int k = 0; k < encryptedletters.length; k++) {
if (encryptedletters[k] == encryptedletters[q]) {
DL[k] = true;
counter++;
}
if (k < (encryptedletters.length - 2)) {
q++;
}
DL[encryptedletters.length - 1] = false;

}
int dif[] = new int[counter];

for (int z = 0; z < DL.length; z++) {
j = 0;
if (DL[z] == true) {

for (int p = 0; p < codes.length; p++) {
if (encryptedletters[z] == codes[p]) {
j = p;
}
}
stop = false;
while (stop == false) {
random = randomDL(alphabet);
if (random == prandom) {
random = randomDL(alphabet);
stop = false;
} else {
stop = true;
}

}
encryptedletters[z] = codes[random - 1];
prandom = random;
dif[h] = random - 1 - j;
h++;

}

}
encryptedword = new String(encryptedletters);
switch (schoice) {
case 0:
save1(DL, num, dif, encryptedword);
break;
case 1:
save2(DL, num, dif, encryptedword);
break;
case 2:
save3(DL, num, dif, encryptedword);
break;
default:
break;
}

System.out.println(encryptedword);
JOptionPane.showMessageDialog(null, "You're Encrypted File Has Been Saved, you can view the encryption in the Text.txt file or at the console window below");

}

//
public static void decrypt(String input, char[] alphabet, char[] codes, boolean[] DL, int[] num, int[] dif) {

char[] inputletters = input.toCharArray();
char[] decryptedletters = new char[inputletters.length];
String decryptedword;
int j;
int y = 0;
for (int k = 0; k < DL.length; k++) {
j = 0;

if (DL[k] == true) {
for (int p = 0; p < codes.length; p++) {
if (inputletters[k] == codes[p]) {
j = p;
}
}
if (dif[y] <= j) {
inputletters[k] = codes[j - dif[y]];
} else if (dif[y] > j) {
inputletters[k] = codes[dif[y] - j];
}
y++;
}

}
for (int z = 0; z < inputletters.length; z++) {

for (int k = 0; k < codes.length; k++) {

if (inputletters[z] == codes[k]) {
decryptedletters[z] = alphabet[k];
}

}


}

decryptedword = new String(decryptedletters);
JOptionPane.showMessageDialog(null, decryptedword);

}

public static void GNC(int[] num, char[] alphabet) {
int z;
Random Z = new Random();
Boolean repeat;
for (int r = 0; r < num.length; r++) {
num[r] = 0;
}

for (int k = 0; k < num.length; k++) {
z = Z.nextInt(alphabet.length) + 1;
repeat = false;

for (int p = 0; p < num.length; p++) {

if (z == num[p]) {
repeat = true;
}

}

if (repeat == true) {
k--;
} else {
num[k] = z;
}

}
}

public static void startup(int num[], char alphabet[], char codes[]) {

for (int t = 0; t < num.length; t++) {
codes[(num[t]) - 1] = alphabet[t];

}

}

public static int randomDL(char[] alphabet) {
int randomnum;
Random generator = new Random();

randomnum = generator.nextInt(alphabet.length) + 1;
return randomnum;
}

public static void save1(boolean[] DL, int[] num, int[] dif, String encryptedword) throws IOException {
String[] nums = new String[num.length];
String[] booleans = new String[DL.length];
String[] differences = new String[dif.length];
for (int y = 0; y < num.length; y++) {
nums[y] = String.valueOf(num[y]);
}
for (int y = 0; y < booleans.length; y++) {
booleans[y] = String.valueOf(DL[y]);
}
for (int y = 0; y < differences.length; y++) {
differences[y] = String.valueOf(dif[y]);
}

try (
PrintWriter out = new PrintWriter("Files1//numbers.txt")) {
for (int x = 0; x < nums.length; x++) {
out.println(nums[x]);
}

}

try (
PrintWriter out = new PrintWriter("Files1//Booleans.txt")) {
for (int x = 0; x < booleans.length; x++) {
out.println(booleans[x]);
}

}

try (
PrintWriter out = new PrintWriter("Files1//LDF.txt")) {

out.println(dif.length);

}

try (
PrintWriter out = new PrintWriter("Files1//Text.txt")) {

out.println(encryptedword);

}

try (
PrintWriter out = new PrintWriter("Files1//DIF.txt")) {
for (int x = 0; x < differences.length; x++) {
out.println(differences[x]);
}

}
}

public static void load1(String input, char[] alphabet, char[] codes, int[] num) throws FileNotFoundException, IOException {
int LDF = 0;
try (BufferedReader in = new BufferedReader(new FileReader("Files1\\Text.txt"))) {// refers to the location of the file

while (in.ready()) {

input = in.readLine();// read the file

}

}

try (BufferedReader in = new BufferedReader(new FileReader("Files1\\LDF.txt"))) {// refers to the location of the file

while (in.ready()) {

LDF = Integer.parseInt(in.readLine());
}

}

try (BufferedReader in = new BufferedReader(new FileReader("Files1\\Numbers.txt"))) {// refers to the location of the file

while (in.ready()) {

for (int p = 0; p < num.length; p++) {
num[p] = Integer.parseInt(in.readLine());
}
}

}
int k = input.length();
boolean DL[] = new boolean[k];
try (BufferedReader in = new BufferedReader(new FileReader("Files1\\Booleans.txt"))) {// refers to the location of the file

while (in.ready()) {

for (int p = 0; p < k; p++) {
DL[p] = Boolean.parseBoolean(in.readLine());
}
}

}
int dif[] = new int[LDF];

try (BufferedReader in = new BufferedReader(new FileReader("Files1\\DIF.txt"))) {// refers to the location of the file

while (in.ready()) {

for (int p = 0; p < LDF; p++) {
dif[p] = Integer.parseInt(in.readLine());
}
}

}

startup(num, alphabet, codes);
decrypt(input, alphabet, codes, DL, num, dif);

}
public static void save2(boolean[] DL, int[] num, int[] dif, String encryptedword) throws IOException {
String[] nums = new String[num.length];
String[] booleans = new String[DL.length];
String[] differences = new String[dif.length];
for (int y = 0; y < num.length; y++) {
nums[y] = String.valueOf(num[y]);
}
for (int y = 0; y < booleans.length; y++) {
booleans[y] = String.valueOf(DL[y]);
}
for (int y = 0; y < differences.length; y++) {
differences[y] = String.valueOf(dif[y]);
}

try (
PrintWriter out = new PrintWriter("Files2//numbers.txt")) {
for (int x = 0; x < nums.length; x++) {
out.println(nums[x]);
}

}

try (
PrintWriter out = new PrintWriter("Files2//Booleans.txt")) {
for (int x = 0; x < booleans.length; x++) {
out.println(booleans[x]);
}

}

try (
PrintWriter out = new PrintWriter("Files2//LDF.txt")) {

out.println(dif.length);

}

try (
PrintWriter out = new PrintWriter("Files2//Text.txt")) {

out.println(encryptedword);

}

try (
PrintWriter out = new PrintWriter("Files2//DIF.txt")) {
for (int x = 0; x < differences.length; x++) {
out.println(differences[x]);
}

}
}

public static void load2(String input, char[] alphabet, char[] codes, int[] num) throws FileNotFoundException, IOException {
int LDF = 0;
try (BufferedReader in = new BufferedReader(new FileReader("Files2\\Text.txt"))) {// refers to the location of the file

while (in.ready()) {

input = in.readLine();// read the file

}

}

try (BufferedReader in = new BufferedReader(new FileReader("Files2\\LDF.txt"))) {// refers to the location of the file

while (in.ready()) {

LDF = Integer.parseInt(in.readLine());
}

}

try (BufferedReader in = new BufferedReader(new FileReader("Files2\\Numbers.txt"))) {// refers to the location of the file

while (in.ready()) {

for (int p = 0; p < num.length; p++) {
num[p] = Integer.parseInt(in.readLine());
}
}

}
int k = input.length();
boolean DL[] = new boolean[k];
try (BufferedReader in = new BufferedReader(new FileReader("Files2\\Booleans.txt"))) {// refers to the location of the file

while (in.ready()) {

for (int p = 0; p < k; p++) {
DL[p] = Boolean.parseBoolean(in.readLine());
}
}

}
int dif[] = new int[LDF];

try (BufferedReader in = new BufferedReader(new FileReader("Files2\\DIF.txt"))) {// refers to the location of the file

while (in.ready()) {

for (int p = 0; p < LDF; p++) {
dif[p] = Integer.parseInt(in.readLine());
}
}

}

startup(num, alphabet, codes);
decrypt(input, alphabet, codes, DL, num, dif);

}

public static void save3(boolean[] DL, int[] num, int[] dif, String encryptedword) throws IOException {
String[] nums = new String[num.length];
String[] booleans = new String[DL.length];
String[] differences = new String[dif.length];
for (int y = 0; y < num.length; y++) {
nums[y] = String.valueOf(num[y]);
}
for (int y = 0; y < booleans.length; y++) {
booleans[y] = String.valueOf(DL[y]);
}
for (int y = 0; y < differences.length; y++) {
differences[y] = String.valueOf(dif[y]);
}

try (
PrintWriter out = new PrintWriter("Files3//numbers.txt")) {
for (int x = 0; x < nums.length; x++) {
out.println(nums[x]);
}

}

try (
PrintWriter out = new PrintWriter("Files3//Booleans.txt")) {
for (int x = 0; x < booleans.length; x++) {
out.println(booleans[x]);
}

}

try (
PrintWriter out = new PrintWriter("Files3//LDF.txt")) {

out.println(dif.length);

}

try (
PrintWriter out = new PrintWriter("Files3//Text.txt")) {

out.println(encryptedword);


}

try (
PrintWriter out = new PrintWriter("Files3//DIF.txt")) {
for (int x = 0; x < differences.length; x++) {
out.println(differences[x]);
}

}
}

public static void load3(String input, char[] alphabet, char[] codes, int[] num) throws FileNotFoundException, IOException {
int LDF = 0;
try (BufferedReader in = new BufferedReader(new FileReader("Files3\\Text.txt"))) {// refers to the location of the file

while (in.ready()) {

input = in.readLine();// read the file

}

}

try (BufferedReader in = new BufferedReader(new FileReader("Files3\\LDF.txt"))) {// refers to the location of the file

while (in.ready()) {

LDF = Integer.parseInt(in.readLine());
}

}

try (BufferedReader in = new BufferedReader(new FileReader("Files3\\Numbers.txt"))) {// refers to the location of the file

while (in.ready()) {

for (int p = 0; p < num.length; p++) {
num[p] = Integer.parseInt(in.readLine());
}
}

}
int k = input.length();
boolean DL[] = new boolean[k];
try (BufferedReader in = new BufferedReader(new FileReader("Files3\\Booleans.txt"))) {// refers to the location of the file

while (in.ready()) {

for (int p = 0; p < k; p++) {
DL[p] = Boolean.parseBoolean(in.readLine());
}
}

}
int dif[] = new int[LDF];

try (BufferedReader in = new BufferedReader(new FileReader("Files3\\DIF.txt"))) {// refers to the location of the file

while (in.ready()) {

for (int p = 0; p < LDF; p++) {
dif[p] = Integer.parseInt(in.readLine());
}
}

}

startup(num, alphabet, codes);
decrypt(input, alphabet, codes, DL, num, dif);

}
}
