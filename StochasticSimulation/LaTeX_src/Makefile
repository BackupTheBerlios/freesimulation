
###
###    Copyright (c)  2002  Peter Biechele, Francesco Petruccione
###
###    Permission is granted to copy, distribute and/or modify this document
###    under the terms of the GNU Free Documentation License, Version 1.1
###    or any later version published by the Free Software Foundation;
###    with no Invariant Sections, with the Front-Cover Texts being 
###    FRONT LIST, and with the Back-Cover Texts being BACK LIST.
###    A copy of the license is included in the section entitled "GNU
###    Free Documentation License".
###

###
### Makefile for the Book "Stochastic Methods for Physics using Java - An Introduction"
###

### 
### Instructions:  Type "make help"
###

##########################################################################################


### Operating System commands
CAT=cat
RM=rm -f

# postscript viewer
GV=gv

### LaTeX commands
LATEX=latex
DVIPS=dvips
XDVI=xdvi
BIBTEX=bibtex
LATEX2HTML=latex2html
TTH=l2h
PS2PDF=ps2pdf

### Variables
# Name of Main TeX File
MAIN_TEX_FILE=SimulationBook

# If you change theis, you have to change it in $(MAIN_TEX_FILE).tex too 
INCLUDE_ONLY_FILENAME=compile_parts.tex

### Chapters of the Book
PREFACE=CD_ROM 

CHAP0=IntroToJava
CHAP0_SHORTCUTS=Intro Introduction
CHAP1=ObjectOriented
CHAP1_SHORTCUTS=Object
CHAP2=PlottingWithJava
CHAP2_SHORTCUTS=Plot
CHAP3=StochasticVariables
CHAP3_SHORTCUTS=Stochastic
CHAP4=DataAnalysis
CHAP4_SHORTCUTS=Data
CHAP5=SamplingOfProbDists
CHAP5_SHORTCUTS=Sampling
CHAP6=MarkovProcesses
CHAP6_SHORTCUTS=Markov
CHAP7=SDE
CHAP7_SHORTCUTS=sde
CHAP8=MolecularDynamics
CHAP8_SHORTCUTS=Molecular
CHAP9=MonteCarlo
CHAP9_SHORTCUTS=Monte Carlo
CHAP10=NonEquilibriumMC
CHAP10_SHORTCUTS=Non NEQ neq
CHAP11=QuantumMechanics
CHAP11_SHORTCUTS=Quant Quantum
CHAP12=RiskManagement
CHAP12_SHORTCUTS=Risk Manage RM

ALL_CHAPTERS=$(CHAP0) $(CHAP1) $(CHAP2) $(CHAP3) $(CHAP4) $(CHAP5) $(CHAP6) $(CHAP7) \
	$(CHAP8) $(CHAP9) $(CHAP10) $(CHAP11) $(CHAP12)

PART1:=$(CHAP0) $(CHAP1) $(CHAP2)
PART2:=$(CHAP3) $(CHAP4) $(CHAP5)
PART3:=$(CHAP6) $(CHAP7)
PART4:=$(CHAP8) $(CHAP9) $(CHAP10)
PART5:=$(CHAP11) $(CHAP12)

PART1_TARGETS=Part1 PART1 part1
PART2_TARGETS=Part2 PART2 part2
PART3_TARGETS=Part3 PART3 part3
PART4_TARGETS=Part4 PART4 part4
PART5_TARGETS=Part5 PART5 part5


APPENDIX=JavaSummary AppendixMainText Listings Solutions GNU_Docu_License GNU_Software_License
APPENDIX_SHORTCUTS=appendix

###
###
### TARGETS
###
###

.PHONY: ps postscript viewdvi dviview Appendix clean html realclean tth book dvi \
	help Help HELP pdf PDF PS info save \
	Chap0 Chap1 Chap2 Chap3 Chap4 Chap5 Chap6 Chap7 Chap8 Chap9 Chap10 Chap11 Chap12\
	$(CHAP0) $(CHAP1) $(CHAP2) $(CHAP3) $(CHAP4) $(CHAP5) $(CHAP6) $(CHAP7) \
	$(CHAP8) $(CHAP9) $(CHAP10) $(CHAP11) $(CHAP12) \
	$(CHAP0_SHORTCUTS) $(CHAP1_SHORTCUTS) $(CHAP2_SHORTCUTS) $(CHAP3_SHORTCUTS) $(CHAP4_SHORTCUTS) \
	$(CHAP5_SHORTCUTS) $(CHAP6_SHORTCUTS) $(CHAP7_SHORTCUTS) $(CHAP8_SHORTCUTS) $(CHAP9_SHORTCUTS) \
	$(CHAP10_SHORTCUTS) $(CHAP11_SHORTCUTS) $(CHAP12_SHORTCUTS)


CLEANUP_SUFFIXES=log dvi bbl blg lof lot toc lol idx aux
CLEANUP_SUFFIXES_FILES=aux bbl blg
### clean
clean:
	@echo " Cleaning up ..."
	@$(RM) $(addprefix $(MAIN_TEX_FILE).,$(CLEANUP_SUFFIXES))
	@$(RM) $(addsuffix .$(CLEANUP_SUFFIXES_FILES),$(PREFACE))
	@for suff in $(CLEANUP_SUFFIXES_FILES); do \
		for chap in $(ALL_CHAPTERS); do \
			$(RM) $$chap.$$suff; \
		done; \
	 done
	@$(RM) $(addsuffix .$(CLEANUP_SUFFIXES_FILES),$(APPENDIX))

REAL_CLEANUP_SUFFIXES=ps pdf
realclean: clean
	@echo " Additionally cleaning up .ps .pdf files ($(INCLUDE_ONLY_FILENAME)) ! "
	@$(RM) $(addprefix $(MAIN_TEX_FILE).,$(REAL_CLEANUP_SUFFIXES))
	@$(RM) $(INCLUDE_ONLY_FILENAME)

preface: $(patsubst %,%.tex,$(PREFACE))
	@echo " Using just Preface, $(PREFACE) ..."
# Generating inclusion file
	@echo "\includeonly{$(patsubst %,%$(COMMA),$(PREFACE))}" >$(INCLUDE_ONLY_FILENAME)
	$(LATEX) $(MAIN_TEX_FILE).tex


info:
	@echo "Information about the make process:"
	@echo ""
	@echo "The filenames of the Chapters are called:"
	@echo "$(PREFACE)"
	@echo "$(ALL_CHAPTERS)"
	@echo "$(APPENDIX)"
	@echo ""
	@echo "Filename of main TeX File:"
	@echo "$(MAIN_TEX_FILE)"
	@echo ""
	@echo "Suffixes for Cleanup and real Cleanup:"
	@echo "$(CLEANUP_SUFFIXES)"
	@echo "$(REAL_CLEANUP_SUFFIXES)"
	@echo ""
	@echo "Suffixes for Chapter Cleanups:"
	@echo "$(CLEANUP_SUFFIXES_FILES)"
	@echo ""
	@echo "Name of intermediate/temporary includeonly{} filename::"
	@echo "$(INCLUDE_ONLY_FILENAME)"
	@echo ""
	@echo "Parts of the book and the chapters contained therein:"
	@echo "Part I  : $(addsuffix .tex  ,$(PART1))"
	@echo "Part II : $(addsuffix .tex  ,$(PART2))"
	@echo "Part III: $(addsuffix .tex  ,$(PART3))"
	@echo "Part IV : $(addsuffix .tex  ,$(PART4))"
	@echo "Part V  : $(addsuffix .tex  ,$(PART5))"

###
### All Chapters individually !
###
### If you use the shortcuts or explicit names, it is always redone.
### If you use the $(MAIN_TEX_FILE).dvi version, only if necessary.
###

COMMA=,
### Appendix
Appendix $(APPENDIX_SHORTCUTS): $(patsubst %,%.tex,$(APPENDIX))
	@echo " Using just the Appendix, $(patsubst %,%.tex,$(APPENDIX)) ..."
# Generating inclusion file
	@echo "\includeonly{$(patsubst %,%$(COMMA),$(APPENDIX))}" >$(INCLUDE_ONLY_FILENAME)
	$(LATEX) $(MAIN_TEX_FILE).tex


### Chapter 0
Chap0 $(CHAP0_SHORTCUTS) $(CHAP0): $(CHAP0).aux
	@echo ""

$(CHAP0).aux : $(CHAP0).tex $(MAIN_TEX_FILE).tex
	@echo " Using just Chapter 0, $(CHAP0) ..."
# Generating inclusion file
	@echo "\includeonly{$(CHAP0)}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(BIBTEX) $(CHAP0)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

### Chapter 1
Chap1 $(CHAP1_SHORTCUTS) $(CHAP1): $(CHAP1).aux
	@echo ""

$(CHAP1).aux : $(CHAP1).tex $(MAIN_TEX_FILE).tex
	@echo " Using just Chapter 1, $(CHAP1) ..."
# Generating inclusion file
	@echo "\includeonly{$(CHAP1)}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(BIBTEX) $(CHAP1)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

### Chapter 2
Chap2 $(CHAP2_SHORTCUTS) $(CHAP2): $(CHAP2).aux
	@echo ""

$(CHAP2).aux : $(CHAP2).tex $(MAIN_TEX_FILE).tex
	@echo " Using just Chapter 2, $(CHAP2) ..."
# Generating inclusion file
	@echo "\includeonly{$(CHAP2)}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(BIBTEX) $(CHAP1)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

### Chapter 3
Chap3 $(CHAP3_SHORTCUTS) $(CHAP3): $(CHAP3).aux
	@echo ""

$(CHAP3).aux :$(CHAP3).tex $(MAIN_TEX_FILE).tex
	@echo " Using just Chapter 3, $(CHAP3) ..."
# Generating inclusion file
	@echo "\includeonly{$(CHAP3)}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(BIBTEX) $(CHAP1)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

### Chapter 4
Chap4 $(CHAP4_SHORTCUTS) $(CHAP4): $(CHAP4).aux
	@echo ""

$(CHAP4).aux :$(CHAP4).tex $(MAIN_TEX_FILE).tex
	@echo " Using just Chapter 4, $(CHAP4) ..."
# Generating inclusion file
	@echo "\includeonly{$(CHAP4)}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(BIBTEX) $(CHAP1)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

### Chapter 5
Chap5 $(CHAP5_SHORTCUTS) $(CHAP5): $(CHAP5).aux
	@echo ""

$(CHAP5).aux :$(CHAP5).tex $(MAIN_TEX_FILE).tex
	@echo " Using just Chapter 5, $(CHAP5) ..."
# Generating inclusion file
	@echo "\includeonly{$(CHAP5)}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(BIBTEX) $(CHAP1)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

### Chapter 6
Chap6 $(CHAP6_SHORTCUTS) $(CHAP6): $(CHAP6).aux
	@echo ""

$(CHAP6).aux :$(CHAP6).tex $(MAIN_TEX_FILE).tex
	@echo " Using just Chapter 6, $(CHAP6) ..."
# Generating inclusion file
	@echo "\includeonly{$(CHAP6)}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(BIBTEX) $(CHAP1)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

### Chapter 7
Chap7 $(CHAP7_SHORTCUTS) $(CHAP7): $(CHAP7).aux
	@echo ""

$(CHAP7).aux :$(CHAP7).tex $(MAIN_TEX_FILE).tex
	@echo " Using just Chapter 7, $(CHAP7) ..."
# Generating inclusion file
	@echo "\includeonly{$(CHAP7)}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(BIBTEX) $(CHAP1)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

### Chapter 8
Chap8 $(CHAP8_SHORTCUTS) $(CHAP8): $(CHAP8).aux
	@echo ""

$(CHAP8).aux :$(CHAP8).tex $(MAIN_TEX_FILE).tex
	@echo " Using just Chapter 8, $(CHAP8) ..."
# Generating inclusion file
	@echo "\includeonly{$(CHAP8)}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(BIBTEX) $(CHAP1)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

### Chapter 9
Chap9 $(CHAP9_SHORTCUTS) $(CHAP9): $(CHAP9).aux
	@echo ""

$(CHAP9).aux :$(CHAP9).tex $(MAIN_TEX_FILE).tex
	@echo " Using just Chapter 9, $(CHAP9) ..."
# Generating inclusion file
	@echo "\includeonly{$(CHAP9)}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(BIBTEX) $(CHAP1)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

### Chapter 10
Chap10 $(CHAP10_SHORTCUTS) $(CHAP10): $(CHAP10).aux
	@echo ""

$(CHAP10).aux :$(CHAP10).tex $(MAIN_TEX_FILE).tex
	@echo " Using just Chapter 10, $(CHAP10) ..."
# Generating inclusion file
	@echo "\includeonly{$(CHAP10)}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(BIBTEX) $(CHAP1)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

### Chapter 11
Chap11 $(CHAP11_SHORTCUTS) $(CHAP11): $(CHAP11).aux
	@echo ""

$(CHAP11).aux :$(CHAP11).tex $(MAIN_TEX_FILE).tex
	@echo " Using just Chapter 11, $(CHAP11) ..."
# Generating inclusion file
	@echo "\includeonly{$(CHAP11)}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(BIBTEX) $(CHAP1)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

### Chapter 12
Chap12 $(CHAP12_SHORTCUTS) $(CHAP12): $(CHAP12).aux
	@echo ""

$(CHAP12).aux :$(CHAP12).tex $(MAIN_TEX_FILE).tex
	@echo " Using just Chapter 12, $(CHAP12) ..."
# Generating inclusion file
	@echo "\includeonly{$(CHAP12)}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(BIBTEX) $(CHAP1)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex


###
### The Parts of the Book ....
###
$(PART1_TARGETS): $(addsuffix .tex,$(PART1))
	@echo ""
	@echo " Making Part 1 of the book using files: $(addsuffix .tex,$(PART1))"
	@echo ""
# Generating inclusion file
	@echo "\includeonly{$(addsuffix $(COMMA),$(PART1))}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	-@for chap in $(PART1); do $(BIBTEX) $$chap; done
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

$(PART2_TARGETS): $(addsuffix .tex,$(PART2))
	@echo ""
	@echo " Making Part 2 of the book using files: $(addsuffix .tex,$(PART2))"
	@echo ""
# Generating inclusion file
	@echo "\includeonly{$(addsuffix $(COMMA),$(PART2))}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	-@for chap in $(PART2); do $(BIBTEX) $$chap; done
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

$(PART3_TARGETS): $(addsuffix .tex,$(PART3))
	@echo ""
	@echo " Making Part 3 of the book using files: $(addsuffix .tex,$(PART3))"
	@echo ""
# Generating inclusion file
	@echo "\includeonly{$(addsuffix $(COMMA),$(PART3))}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	-@for chap in $(PART3); do $(BIBTEX) $$chap; done
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

$(PART4_TARGETS): $(addsuffix .tex,$(PART4))
	@echo ""
	@echo " Making Part 4 of the book using files: $(addsuffix .tex,$(PART4))"
	@echo ""
# Generating inclusion file
	@echo "\includeonly{$(addsuffix $(COMMA),$(PART4))}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	-@for chap in $(PART4); do $(BIBTEX) $$chap; done
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex

$(PART5_TARGETS): $(addsuffix .tex,$(PART5))
	@echo ""
	@echo " Making Part 5 of the book using files: $(addsuffix .tex,$(PART5))"
	@echo ""
# Generating inclusion file
	@echo "\includeonly{$(addsuffix $(COMMA),$(PART5))}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	-@for chap in $(PART5); do $(BIBTEX) $$chap; done
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex


###
### The Printed Formats ....
###

### Postscript File using dvips
PS ps postscript $(MAIN_TEX_FILE).ps:
	@echo " Making Postscript file ..."
	@echo " I do not check, if a tex file has changed !"
	@$(DVIPS) $(MAIN_TEX_FILE).dvi

### PDF Format using pdf2ps
pdf PDF: $(MAIN_TEX_FILE).ps
	@echo " Making PDF Format of book ..."
	@echo " We are producing PDF V1.3 format, meaning Acrobat Reader V4.x compatibility."
	@$(PS2PDF) -dCompatibility=1.3 $(MAIN_TEX_FILE).ps $(MAIN_TEX_FILE).pdf

###
### The view Targets ....
###

### view ps file
view psview:	$(MAIN_TEX_FILE).ps
	@echo " Starting viewer for ps using $(GV) !"
	@$(GV) $(MAIN_TEX_FILE).ps

### View dvi file
viewdvi dviview: 
	@echo " Starting viewer for dvi using $(XDVI) !"
	@if [ ! -e $(MAIN_TEX_FILE).dvi ]; then \
	    echo " ATTENTION: First create the dvi file !! "; exit 1; fi
	@$(XDVI) $(MAIN_TEX_FILE).dvi


###
### The Interactive / Internet Formats ....
###

### HTML 
## ATTENTION: Takes a LONG time ....
html: $(MAIN_TEX_FILE).tex
	$(LATEX2HTML) -toc_depth 2 -long_titles 3 -split +3 -white \
		-transparent -image_type png -tmp /tmp $(MAIN_TEX_FILE).tex

### tth (HTML)
tth: $(MAIN_TEX_FILE).tex
	$(TTH) $(MAIN_TEX_FILE) -a -e1




###
### The complete book target
###

### The Whole Book
dvi book: realclean
	@echo " Compiling the whole book ..."
	@echo " First of all, I have cleaned up everything."
# Generating inclusion file
	@echo "%\includeonly{}" >$(INCLUDE_ONLY_FILENAME)
	@$(LATEX) $(MAIN_TEX_FILE).tex
	-@for chap in $(ALL_CHAPTERS); do $(BIBTEX) $$chap; done
	@$(LATEX) $(MAIN_TEX_FILE).tex
	@$(LATEX) $(MAIN_TEX_FILE).tex


### Help Screen
help HELP Help:
	@echo "### "
	@echo "### The following targets exist:"
	@echo "###"
	@echo "### help 	--	This help !"
	@echo "###"
	@echo "### book / dvi 	--	Make the complete book (.dvi file, bibtex used)"
	@echo "### ps		--	Make .ps file from the .dvi file"
	@echo "### pdf		--	Make .pdf from .ps file"
	@echo "### html	--	Make html files from .tex using latex2html"
	@echo "###"
	@echo "### Preface	--	Make the .dvi preface from all .tex files (also use Bibtex)"
	@echo "### Appendix	--	Make the .dvi appendix from all .tex files (also use Bibtex)"
	@echo "### Chap0	--	Make Chap0 .dvi from all .tex files (also use Bibtex)"
	@echo "### The same up to "Chap12" !!"
	@echo "### Part1	--	Make just Part 1 of Book"
	@echo "### The same up to "Part5" !! "
	@echo "###"
