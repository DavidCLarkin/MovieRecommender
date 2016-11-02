# AutoComplete

Functions of the program:
  The user is presented with an option list in which they can search for 1 term, many terms and the weight of the term
  This implements the AutoComplete interface. Depending on the user's input (1,2 or 3) a different method is called from
  different classes. BruteAutocomplete's time to execution is about ~2 seconds. QuickAutocomplete's time to execution is 
  ~0.3 milliseconds.  

Testing:
  Testing negatives of weights so that it can't be negative, tested various getters and setters for the Term class, and the TermList class   to return the ArrayList. Also tested algorithms for the AutoComplete interface while being implemented.
