
##
## Makefile for the Book
##

BIB_SOURCE="IntroToJava.tex ObjectOriented.tex PlottingWithJava.tex StochasticVariables.tex DataAnalysis.tex SamplingOfProbDists.tex MarkovProcesses.tex SDE.tex Chap7.tex Chap8.tex Chap9.tex Chap10.tex Chap11.tex JavaSummary.tex Listings.tex Solutions.tex"

bib: 	
##$(BIB_SOURCE)
	for LIST in $(BIB_SOURCE)
	do 
		echo $(LIST)
	done

html: V_98.tex
	latex2html  -toc_depth 2 -long_titles 3 -split +3 -white -transparent -image_type gif -tmp $(HOME) V_98.tex


tth: V_98.tex
	l2h V_98 -a -e1

