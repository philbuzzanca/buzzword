# buzzword
Improvements on CSE219 final project

Note: to run this on your own computer, a "users" directory must be created within the buzzword directory (i.e. alongside the /src/ package)

I used this repository to teach myself some new things in order to improve and cleanup an existing project.

My first change was to organize my classes better. Previously, all of my Java classes and XML files were direct children of my 'src/sample' package.
I renamed the default "sample" package to Buzzword for clarity, and attempted to implement the MVC pattern to split up my classes into
a more organized structure. I gave the XML files their own package within the view package.

These changes required some refactoring. IntelliJ's refactor tool was helpful in speeding this up but it wasn't perfect, and I had to
troubleshoot some errors such as properly loading my XML scenes as the code no longer knew where to look for them.

When it came to implenting a very basic graph structure, I created custom BoggleGraph and BoggleVertex classes. Because edges aren't
weighted nor directed in my implementation of boggle, a class for them was not necessary. The graph class keeps a list of all vertices
in a HashSet and keeps track of the total number of vertices.

The BoggleVertex class has a char field called letter to represent the letter of the boggle tile, and its list of neighbors is kept in
an ArrayList.

In my "BoggleSolver" class which containst several methods for finding words in the board and loading dictionaries, I implemented the
solveGraph method. This method takes my BoggleGraph class as a parameter as well as the currently used dictionary, and returns a Set of
Strings containing all legal words on the board. I implemented this by going down the list of words in the dictionary, and recursively 
searching each vertex to see if it has a neighboring node containing the next letter of the string.

Attempting to reimplement the project using a graph instead of an array taught me a lot about my own coding habits. I realized that my
code was not as easy to update as I expectd it to be. Too many of my methods were dependent on eachother to easily update one without having
to rework another. What I should have done, and intend to do in future projects, is limit this dependency by having my methods talk to a 
central controller class rather than communicate directly with eachother.

In addition to learning better coding practices, this was my first experience working with git branches. Because I wanted to leave my original
working project intact, I worked on my organization and graph updates in branches rather than in the master branch. Once I was sure my new
features were functional and didn't break my project, I pulled them in to the master branch.
