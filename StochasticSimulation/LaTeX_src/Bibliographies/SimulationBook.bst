%% 
%% This is file `peter.bst',
%% generated with the docstrip utility.
%% 
%% The original source files were:
%% 
%% merlin.mbs  (with options: `head,exlang,ay,nat,vonx,nm-revf,note-yr,vol-bf,isbn,german,ger-phd,and-xcom,nfss,{}')
%% german.mbs  (with options: `exlang,ay,nat,vonx,nm-revf,note-yr,vol-bf,isbn,german,ger-phd,and-xcom,nfss,{}')
%% physjour.mbs  (with options: `exlang,ay,nat,vonx,nm-revf,note-yr,vol-bf,isbn,german,ger-phd,and-xcom,nfss,{}')
%% photjour.mbs  (with options: `exlang,ay,nat,vonx,nm-revf,note-yr,vol-bf,isbn,german,ger-phd,and-xcom,nfss,{}')
%% merlin.mbs  (with options: `tail,exlang,ay,nat,vonx,nm-revf,note-yr,vol-bf,isbn,german,ger-phd,and-xcom,nfss,{}')
%% ---------------------------------------- 
%% *** Peters Diplomarbeits Style *** 
%% 
 %-------------------------------------------------------------------
 % The original source file contains the following version information:
 % \ProvidesFile{merlin.mbs}[1996/02/05 3.7 (PWD)]
 %
 % NOTICE:
 % This file may be used for non-profit purposes.
 % It may not be distributed in exchange for money,
 %   other than distribution costs.
 %
 % The author provides it `as is' and does not guarantee it in any way.
 %
 % Copyright (C) 1994, 1995, 1996 Patrick W. Daly
 %-------------------------------------------------------------------
 %   For use with BibTeX version 0.99a or later
 %-------------------------------------------------------------------
 % This bibliography style file is intended for texts in
 %   GERMAN
 % This is an author-year citation style bibliography. As such, it is
 % non-standard LaTeX, and requires a special package file to function properly.
 % Such a package is    natbib.sty   by Patrick W. Daly
 % The form of the \bibitem entries is
 %   \bibitem[Jones et al.(1990)]{key}...
 %   \bibitem[Jones et al.(1990)Jones, Baker, and Smith]{key}...
 % The essential feature is that the label (the part in brackets) consists
 % of the author names, as they should appear in the citation, with the year
 % in parentheses following. There must be no space before the opening
 % parenthesis!
 % With natbib v5.3, a full list of authors may also follow the year.
 % In natbib.sty, it is possible to define the type of enclosures that is
 % really wanted (brackets or parentheses), but in either case, there must
 % be parentheses in the label.
 % The \cite command functions as follows:
 %   \cite{key} ==>>                Jones et al. (1990)
 %   \cite[]{key} ==>>              (Jones et al., 1990)
 %   \cite[chap. 2]{key} ==>>       (Jones et al., 1990, chap. 2)
 %   \cite[e.g.][]{key} ==>>        (e.g. Jones et al., 1990)
 %   \cite[e.g.][p. 32]{key} ==>>   (e.g. Jones et al., p. 32)
 %   \citeauthor{key}               Jones et al.
 %   \citefullauthor{key}           Jones, Baker, and Smith
 %   \citeyear{key}                 1990
 %---------------------------------------------------------------------

ENTRY
  { address
    author
    booktitle
    chapter
    edition
    editor
    howpublished
    institution
    isbn
    journal
    key
    month
    note
    number
    organization
    pages
    publisher
    school
    series
    title
    type
    volume
    year
  }
  {}
  { label extra.label sort.label short.list }

INTEGERS { output.state before.all mid.sentence after.sentence after.block }

FUNCTION {init.state.consts}
{ #0 'before.all :=
  #1 'mid.sentence :=
  #2 'after.sentence :=
  #3 'after.block :=
}

STRINGS { s t }

FUNCTION {output.nonnull}
{ 's :=
  output.state mid.sentence =
    { ", " * write$ }
    { output.state after.block =
        { add.period$ write$
          newline$
          "\newblock " write$
        }
        { output.state before.all =
            'write$
            { add.period$ " " * write$ }
          if$
        }
      if$
      mid.sentence 'output.state :=
    }
  if$
  s
}

FUNCTION {output}
{ duplicate$ empty$
    'pop$
    'output.nonnull
  if$
}

FUNCTION {output.check}
{ 't :=
  duplicate$ empty$
    { pop$ "empty " t * " in " * cite$ * warning$ }
    'output.nonnull
  if$
}

FUNCTION {fin.entry}
{ add.period$
  write$
  newline$
}

FUNCTION {new.block}
{ output.state before.all =
    'skip$
    { after.block 'output.state := }
  if$
}

FUNCTION {new.sentence}
{ output.state after.block =
    'skip$
    { output.state before.all =
        'skip$
        { after.sentence 'output.state := }
      if$
    }
  if$
}

FUNCTION {add.blank}
{  " " * before.all 'output.state :=
}

FUNCTION {date.block}
{
  new.block
}

FUNCTION {not}
{   { #0 }
    { #1 }
  if$
}

FUNCTION {and}
{   'skip$
    { pop$ #0 }
  if$
}

FUNCTION {or}
{   { pop$ #1 }
    'skip$
  if$
}

FUNCTION {non.stop}
{ duplicate$
   "}" * add.period$
   #-1 #1 substring$ "." =
}

FUNCTION {new.block.checkb}
{ empty$
  swap$ empty$
  and
    'skip$
    'new.block
  if$
}

FUNCTION {field.or.null}
{ duplicate$ empty$
    { pop$ "" }
    'skip$
  if$
}

FUNCTION {emphasize}
{ duplicate$ empty$
    { pop$ "" }
    { "\emph{" swap$ * "}" * }
  if$
}

FUNCTION {bolden}
{ duplicate$ empty$
    { pop$ "" }
    { "\textbf{" swap$ * "}" * }
  if$
}

FUNCTION {capitalize}
{ "u" change.case$ "t" change.case$ }

FUNCTION {space.word}
{ " " swap$ * " " * }

 % Here are the language-specific definitions for explicit words.
 % Each function has a name bbl.xxx where xxx is the English word.
 %-------------------------------------------------------------------
 % The original source file contains the following version information:
 % \ProvidesFile{german.mbs}[1995/11/02 1.5 (PWD)]
 % Copyright (C) 1994, 1995 Patrick W. Daly
 %-------------------------------------------------------------------

 % The language selected here is GERMAN
FUNCTION {bbl.and}
{ "und"}

FUNCTION {bbl.editors}
{ "Herausgeber" }

FUNCTION {bbl.editor}
{ "Herausgeber" }

FUNCTION {bbl.edby}
{ "herausgegeben von" }

FUNCTION {bbl.edition}
{ "Auf\/lage" }

FUNCTION {bbl.volume}
{ "Band" }

FUNCTION {bbl.of}
{ "von" }

FUNCTION {bbl.number}
{ "Nummer" }

FUNCTION {bbl.nr}
{ "Nr." }

FUNCTION {bbl.in}
{ "in" }

FUNCTION {bbl.pages}
{ "Seiten" }

FUNCTION {bbl.page}
{ "Seite" }

FUNCTION {bbl.chapter}
{ "Kapitel" }

FUNCTION {bbl.techrep}
{ "{Technischer Bericht}" }

FUNCTION {bbl.mthesis}
{ "Diplomarbeit" }

FUNCTION {bbl.phdthesis}
{ "Doktorarbeit" }

FUNCTION {bbl.first}
{ "Erste" }

FUNCTION {bbl.second}
{ "Zweite" }

FUNCTION {bbl.third}
{ "Dritte" }

FUNCTION {bbl.fourth}
{ "Vierte" }

FUNCTION {bbl.fifth}
{ "F\^^b{u}nfte" }

FUNCTION {bbl.th}
{ "." }

MACRO {jan} {"Januar"}

MACRO {feb} {"Februar"}

MACRO {mar} {"M\^^b{a}rz"}

MACRO {apr} {"April"}

MACRO {may} {"Mai"}

MACRO {jun} {"Juni"}

MACRO {jul} {"Juli"}

MACRO {aug} {"August"}

MACRO {sep} {"September"}

MACRO {oct} {"Oktober"}

MACRO {nov} {"November"}

MACRO {dec} {"Dezember"}

 % End of language definition file

 %-------------------------------------------------------------------
 % The original source file contains the following version information:
 % \ProvidesFile{physjour.mbs}[1995/10/12 1.2 (PWD)]
 % Copyright (C) 1994, 1995 Patrick W. Daly
 %-------------------------------------------------------------------
MACRO {advp} {"Advances in Physics"}
MACRO {ajp} {"American Journal of Physics"}
MACRO {ajm} {"American Journal of Mathematics"}
MACRO {ag} {"Annales Geophysic\ae"}
MACRO {am} {"Annals of Mathematics"}
MACRO {ap} {"Annals of Physics"}
MACRO {adp} {"Annalen der Physik"}
MACRO {ao} {"Applied Optics"}
MACRO {apl} {"Applied Physics Letters"}
MACRO {app} {"Astroparticle Physics"}
MACRO {apj} {"Astrophysical Journal"}
MACRO {baps} {"Bulletin of the American Physical Society"}
MACRO {cpc} {"Computer Physics Communications"}
MACRO {cmp} {"Communications in Mathematical Physics"}
MACRO {cppcf} {"Comments on Plasma Physics and Controlled Fusion"}
MACRO {cqg} {"Classical and Quantum Gravity"}
MACRO {cra} {"Comptes Rendus des S{\'e}ances de l'Acad{\'e}mie des Sciences A"}
MACRO {fed} {"Fusion Engineering and Design"}
MACRO {ft} {"Fusion Technology"}
MACRO {grg} {"General Relativity and Gravitation"}
MACRO {ieeens} {"IEEE Transactions on Nuclear Science"}
MACRO {ieeeps} {"IEEE Transactions on Plasma Science"}
MACRO {ijimw} {"International Journal of Infrared and Millimeter Waves"}
MACRO {ip} {"Infrared Physics"}
MACRO {inc} {"Il Nouvo Cimento"}
MACRO {jap} {"Journal of Applied Physics"}
MACRO {jcp} {"Journal of Computational Physics"}
MACRO {jetp} {"Soviet Physics-JETP"}
MACRO {jfe} {"Journal of Fusion Energy"}
MACRO {jfm} {"Journal of Fluid Mechanics"}
MACRO {jgr} {"Journal of Geophysical Research"}
MACRO {grl} {"Geophysical Research Letters"}
MACRO {jmp} {"Journal of Mathematical Physics"}
MACRO {jne} {"Journal of Nuclear Energy"}
MACRO {jnec} {"Journal of Nuclear Energy, Part C: Plasma Physics, Accelerators, Thermonuclear Research"}
MACRO {jnm} {"Journal of Nuclear Materials"}
MACRO {josa} {"Journal of the Optical Society of America"}
MACRO {jpp} {"Journal of Plasma Physics"}
MACRO {jpsj} {"Journal of the Physical Society of Japan"}
MACRO {jvst} {"Journal of Vacuum Science and Technology"}
MACRO {nedf} {"Nuclear Engineering and Design/Fusion"}
MACRO {nf} {"Nuclear Fusion"}
MACRO {nim} {"Nuclear Instruments and Methods"}
MACRO {nimpr} {"Nuclear Instruments and Methods in Physics Research"}
MACRO {np} {"Nuclear Physics"}
MACRO {npb} {"Nuclear Physics B"}
MACRO {npg} {"Nonlinear Processes in Geophysics"}
MACRO {nt/f} {"Nuclear Technology/Fusion"}
MACRO {pf} {"Physics of Fluids"}
MACRO {pfa} {"Physics of Fluids A: Fluid Dynamics"}
MACRO {pfb} {"Physics of Fluids B: Plasma Physics"}
MACRO {pl} {"Physics Letters"}
MACRO {pla} {"Physics Letters A"}
MACRO {plb} {"Physics Letters B"}
MACRO {prep} {"Physics Reports"}
MACRO {pnas} {"Proceedings of the National Academy of Sciences of the USA"}
MACRO {pp} {"Plasma Physics"}
MACRO {ppcf} {"Plasma Physics and Controlled Fusion"}
MACRO {prl} {"Physical Review Letters"}
MACRO {pr} {"Physical Review"}
MACRO {pra} {"Physical Review A"}
MACRO {prb} {"Physical Review B"}
MACRO {prc} {"Physical Review C"}
MACRO {prd} {"Physical Review D"}
MACRO {pre} {"Physical Review E"}
MACRO {ps} {"Physica Scripta"}
MACRO {rmp} {"Reviews of Modern Physics"}
MACRO {rsi} {"Review of Scientific Instruments"}
MACRO {sjpp} {"Soviet Journal of Plasma Physics"}
MACRO {spd} {"Soviet Physics-Doklady"}
MACRO {sptp} {"Soviet Physics-Technical Physics"}
MACRO {spu} {"Soviet Physics-Uspeki"}
 % End of journal definition file

 %-------------------------------------------------------------------
 % The original source file contains the following version information:
 % \ProvidesFile{photjour.mbs}[1995/10/11 1.0 (PWD)]

MACRO {bell} {"Bell Systems Technical Journal"}
MACRO {ell} {"Electronics Letters"}
MACRO {jas} {"Journal of Applied Spectroscopy"}
MACRO {jqe} {"IEEE Journal of Quantum Electronics"}
MACRO {jlwt} {"Journal of Lightwave Technology"}
MACRO {jmo} {"Journal of Modern Optics"}
MACRO {josaa} {"Journal of the Optical Society of America~A"}
MACRO {josab} {"Journal of the Optical Society of America~B"}
MACRO {jdp} {"Journal de Physique"}
MACRO {nat} {"Nature (London)"}
MACRO {oc} {"Optics Communications"}
MACRO {ol} {"Optics Letters"}
MACRO {phtl} {"IEEE Photonics Technology Letters"}
MACRO {pspie} {"Proceedings of the Society for Photo-Optical Instruments Engineers"}
MACRO {sse} {"Solid-State Electronics"}
MACRO {sjot} {"Soviet Journal of Optical Technology"}
MACRO {sjqe} {"Soviet Journal of Quantum Electronics"}
MACRO {sleb} {"Soviet Physics -- Lebedev Institute Reports"}
MACRO {stph} {"Soviet Physics -- Technical Physics"}
MACRO {stphl} {"Soviet Technical Physics Letters"}
MACRO {vr} {"Vision Research"}
%MACRO {zph} {"Zeitschrift f{\"u}r Physik"}
%MACRO {zphb} {"Zeitschrift f{\"u}r Physik~B"}
%MACRO {zphd} {"Zeitschrift f{\"u}r Physik~D"}

MACRO {CLEO} {"Conf. on Lasers and Electro-Optics"}
MACRO {ASSL} {"Advanced Solid State Lasers"}
MACRO {OSA}  {"Optical Society of America"}
 % End of journal definition file

MACRO {acmcs} {"ACM Computing Surveys"}

MACRO {acta} {"Acta Informatica"}

MACRO {cacm} {"Communications of the ACM"}

MACRO {ibmjrd} {"IBM Journal of Research and Development"}

MACRO {ibmsj} {"IBM Systems Journal"}

MACRO {ieeese} {"IEEE Transactions on Software Engineering"}

MACRO {ieeetc} {"IEEE Transactions on Computers"}

MACRO {ieeetcad}
 {"IEEE Transactions on Computer-Aided Design of Integrated Circuits"}

MACRO {ipl} {"Information Processing Letters"}

MACRO {jacm} {"Journal of the ACM"}

MACRO {jcss} {"Journal of Computer and System Sciences"}

MACRO {scp} {"Science of Computer Programming"}

MACRO {sicomp} {"SIAM Journal on Computing"}

MACRO {tocs} {"ACM Transactions on Computer Systems"}

MACRO {tods} {"ACM Transactions on Database Systems"}

MACRO {tog} {"ACM Transactions on Graphics"}

MACRO {toms} {"ACM Transactions on Mathematical Software"}

MACRO {toois} {"ACM Transactions on Office Information Systems"}

MACRO {toplas} {"ACM Transactions on Programming Languages and Systems"}

MACRO {tcs} {"Theoretical Computer Science"}

INTEGERS { nameptr namesleft numnames }

FUNCTION {format.names}
{ 's :=
  #1 'nameptr :=
  s num.names$ 'numnames :=
  numnames 'namesleft :=
    { namesleft #0 > }
    { s nameptr
      "{vv~}{ll}{, jj}{, ff}" format.name$ 't :=
      nameptr #1 >
        {
          namesleft #1 >
            { ", " * t * }
            {
              t "others" =
                { " et~al." * }
                { bbl.and space.word * t * }
              if$
            }
          if$
        }
        't
      if$
      nameptr #1 + 'nameptr :=
      namesleft #1 - 'namesleft :=
    }
  while$
}

FUNCTION {format.names.ed}
{ 's :=
  #1 'nameptr :=
  s num.names$ 'numnames :=
  numnames 'namesleft :=
    { namesleft #0 > }
    { s nameptr
      "{ff~}{vv~}{ll}{, jj}"
      format.name$ 't :=
      nameptr #1 >
        {
          namesleft #1 >
            { ", " * t * }
            {
              t "others" =
                { " et~al." * }
                { bbl.and space.word * t * }
              if$
            }
          if$
        }
        't
      if$
      nameptr #1 + 'nameptr :=
      namesleft #1 - 'namesleft :=
    }
  while$
}

FUNCTION {format.key}
{ empty$
    { key field.or.null }
    { "" }
  if$
}

FUNCTION {format.authors}
{ author empty$
    { "" }
    {
      author format.names
    }
  if$
}

FUNCTION {format.editors}
{ editor empty$
    { "" }
    {
      editor format.names
      editor num.names$ #1 >
        { ", " * bbl.editors * }
        { ", " * bbl.editor * }
      if$
    }
  if$
}

FUNCTION {format.in.editors}
{ editor empty$
    { "" }
    { editor format.names.ed
      editor num.names$ #1 >
        { ", " * bbl.editors * }
        { ", " * bbl.editor * }
      if$
    }
  if$
}

FUNCTION {format.isbn}
{ isbn empty$
    { "" }
    {
      new.block
      "ISBN " isbn * }
  if$
}

FUNCTION {format.title}
{ title empty$
    { "" }
    { title } 
%%% Geaendert !!! P.B. 14.4.96
%%%    "t" change.case$}
  if$
}

FUNCTION {format.full.names}
{'s :=
  #1 'nameptr :=
  s num.names$ 'numnames :=
  numnames 'namesleft :=
    { namesleft #0 > }
    { s nameptr
      "{vv~}{ll}" format.name$ 't :=
      nameptr #1 >
        {
          namesleft #1 >
            { ", " * t * }
            {
              t "others" =
                { " et~al." * }
                { bbl.and space.word * t * }
              if$
            }
          if$
        }
        't
      if$
      nameptr #1 + 'nameptr :=
      namesleft #1 - 'namesleft :=
    }
  while$
}

FUNCTION {author.editor.key.full}
{ author empty$
    { editor empty$
        { key empty$
            { cite$ #1 #3 substring$ }
            'key
          if$
        }
        { editor format.full.names }
      if$
    }
    { author format.full.names }
  if$
}

FUNCTION {author.key.full}
{ author empty$
    { key empty$
         { cite$ #1 #3 substring$ }
          'key
      if$
    }
    { author format.full.names }
  if$
}

FUNCTION {editor.key.full}
{ editor empty$
    { key empty$
         { cite$ #1 #3 substring$ }
          'key
      if$
    }
    { editor format.full.names }
  if$
}

FUNCTION {make.full.names}
{ type$ "book" =
  type$ "inbook" =
  or
    'author.editor.key.full
    { type$ "proceedings" =
        'editor.key.full
        'author.key.full
      if$
    }
  if$
}

FUNCTION {output.bibitem}
{ newline$
  "\bibitem[" write$
  label write$
  ")" make.full.names duplicate$ short.list =
     { pop$ }
     { * }
   if$
  "]{" * write$
  cite$ write$
  "}" write$
  newline$
  ""
  before.all 'output.state :=
}

FUNCTION {n.dashify}
{ 't :=
  ""
    { t empty$ not }
    { t #1 #1 substring$ "-" =
        { t #1 #2 substring$ "--" = not
            { "--" *
              t #2 global.max$ substring$ 't :=
            }
            {   { t #1 #1 substring$ "-" = }
                { "-" *
                  t #2 global.max$ substring$ 't :=
                }
              while$
            }
          if$
        }
        { t #1 #1 substring$ *
          t #2 global.max$ substring$ 't :=
        }
      if$
    }
  while$
}

FUNCTION {word.in}
{ bbl.in capitalize
  " " * }

FUNCTION {format.date}
{ year duplicate$ empty$
    { "empty year in " cite$ * "; set to ????" * warning$
       pop$ "????" }
    'skip$
  if$
  extra.label *
}

FUNCTION {format.btitle}
{ title emphasize
}

FUNCTION {tie.or.space.connect}
{ duplicate$ text.length$ #3 <
    { "~" }
    { " " }
  if$
  swap$ * *
}

FUNCTION {either.or.check}
{ empty$
    'pop$
    { "can't use both " swap$ * " fields in " * cite$ * warning$ }
  if$
}

FUNCTION {format.bvolume}
{ volume empty$
    { "" }
    { bbl.volume volume tie.or.space.connect
      series empty$
        'skip$
        { bbl.of space.word * series emphasize * }
      if$
      "volume and number" number either.or.check
    }
  if$
}

FUNCTION {format.number.series}
{ volume empty$
    { number empty$
        { series field.or.null }
        { output.state mid.sentence =
            { bbl.number }
            { bbl.number capitalize }
          if$
          number tie.or.space.connect
          series empty$
            { "there's a number but no series in " cite$ * warning$ }
            { bbl.in space.word * series * }
          if$
        }
      if$
    }
    { "" }
  if$
}

FUNCTION {is.num}
{ chr.to.int$
  duplicate$ "0" chr.to.int$ < not
  swap$ "9" chr.to.int$ > not and
}

FUNCTION {extract.num}
{ duplicate$ 't :=
  "" 's :=
  { t empty$ not }
  { t #1 #1 substring$
    t #2 global.max$ substring$ 't :=
    duplicate$ is.num
      { s swap$ * 's := }
      { pop$ "" 't := }
    if$
  }
  while$
  s empty$
    'skip$
    { pop$ s }
  if$
}

FUNCTION {convert.edition}
{ edition extract.num "l" change.case$ 's :=
  s "first" = s "1" = or
    { bbl.first 't := }
    { s "second" = s "2" = or
        { bbl.second 't := }
        { s "third" = s "3" = or
            { bbl.third 't := }
            { s "fourth" = s "4" = or
                { bbl.fourth 't := }
                { s "fifth" = s "5" = or
                    { bbl.fifth 't := }
                    { s #1 #1 substring$ is.num
                        { s bbl.th * 't := }
                        { edition 't := }
                      if$
                    }
                  if$
                }
              if$
            }
          if$
        }
      if$
    }
  if$
  t
}

FUNCTION {format.edition}
{ edition empty$
    { "" }
    { output.state mid.sentence =
        { convert.edition "l" change.case$ " " * bbl.edition * }
        { convert.edition "t" change.case$ " " * bbl.edition * }
      if$
    }
  if$
}

INTEGERS { multiresult }

FUNCTION {multi.page.check}
{ 't :=
  #0 'multiresult :=
    { multiresult not
      t empty$ not
      and
    }
    { t #1 #1 substring$
      duplicate$ "-" =
      swap$ duplicate$ "," =
      swap$ "+" =
      or or
        { #1 'multiresult := }
        { t #2 global.max$ substring$ 't := }
      if$
    }
  while$
  multiresult
}

FUNCTION {format.pages}
{ pages empty$
    { "" }
    { pages multi.page.check
        { bbl.pages pages n.dashify tie.or.space.connect }
        { bbl.page pages tie.or.space.connect }
      if$
    }
  if$
}

FUNCTION {format.vol.num.pages}
{ volume field.or.null
  bolden
  number empty$
    'skip$
    {
      "(" number * ")" * *
      volume empty$
        { "there's a number but no volume in " cite$ * warning$ }
        'skip$
      if$
    }
  if$
  pages empty$
    'skip$
    { duplicate$ empty$
        { pop$ format.pages }
        { ":" * pages n.dashify * }
      if$
    }
  if$
}

FUNCTION {format.chapter.pages}
{ chapter empty$
    'format.pages
    { type empty$
        { bbl.chapter }
        { type "l" change.case$ }
      if$
      chapter tie.or.space.connect
      pages empty$
        'skip$
        { ", " * format.pages * }
      if$
    }
  if$
}

FUNCTION {format.in.ed.booktitle}
{ booktitle empty$
    { "" }
    { editor empty$
        { word.in booktitle emphasize * }
        { word.in format.in.editors * ", " *
          booktitle emphasize * }
      if$
    }
  if$
}

FUNCTION {format.thesis.type}
{ type empty$
    'skip$
    { pop$
      type "t" change.case$
    }
  if$
}

FUNCTION {format.tr.number}
{ type empty$
    { bbl.techrep }
    'type
  if$
  number empty$
    { "t" change.case$ }
    { number tie.or.space.connect }
  if$
}

FUNCTION {format.article.crossref}
{
  word.in
  " \cite{" * crossref * "}" *
}

FUNCTION {format.book.crossref}
{ volume empty$
    { "empty volume in " cite$ * "'s crossref of " * crossref * warning$
      word.in
    }
    { bbl.volume capitalize
      volume tie.or.space.connect
      bbl.of space.word *
    }
  if$
  " \cite{" * crossref * "}" *
}

FUNCTION {format.incoll.inproc.crossref}
{
  word.in
  " \cite{" * crossref * "}" *
}

FUNCTION {article}
{ output.bibitem
  format.authors "author" output.check
  author format.key output
  new.block
  format.title "title" output.check
  new.block
  crossref missing$
    { journal
      emphasize
      "journal" output.check
      format.vol.num.pages output
      format.date "year" output.check
    }
    { format.article.crossref output.nonnull
      format.pages output
    }
  if$
  new.block
  note output
  fin.entry
}

FUNCTION {book}
{ output.bibitem
  author empty$
    { format.editors "author and editor" output.check
      editor format.key output
    }
    { format.authors output.nonnull
      crossref missing$
        { "author and editor" editor either.or.check }
        'skip$
      if$
    }
  if$
  new.block
  format.btitle "title" output.check
  crossref missing$
    { format.bvolume output
      new.block
      format.number.series output
      new.sentence
      publisher "publisher" output.check
      address output
    }
    {
      new.block
      format.book.crossref output.nonnull
    }
  if$
  format.edition output
  format.date "year" output.check
  format.isbn output
  new.block
  note output
  fin.entry
}

FUNCTION {booklet}
{ output.bibitem
  format.authors output
  author format.key output
  new.block
  format.title "title" output.check
  new.block
  howpublished output
  address output
  format.date "year" output.check
  new.block
  note output
  fin.entry
}

FUNCTION {inbook}
{ output.bibitem
  author empty$
    { format.editors "author and editor" output.check
      editor format.key output
    }
    { format.authors output.nonnull
      crossref missing$
        { "author and editor" editor either.or.check }
        'skip$
      if$
    }
  if$
  new.block
  format.btitle "title" output.check
  crossref missing$
    { format.bvolume output
      format.chapter.pages "chapter and pages" output.check
      new.block
      format.number.series output
      new.sentence
      publisher "publisher" output.check
      address output
    }
    {
      format.chapter.pages "chapter and pages" output.check
      new.block
      format.book.crossref output.nonnull
    }
  if$
  format.edition output
  format.date "year" output.check
  format.isbn output
  new.block
  note output
  fin.entry
}

FUNCTION {incollection}
{ output.bibitem
  format.authors "author" output.check
  author format.key output
  new.block
  format.title "title" output.check
  new.block
  crossref missing$
    { format.in.ed.booktitle "booktitle" output.check
      format.bvolume output
      format.number.series output
      format.chapter.pages output
      new.sentence
      publisher "publisher" output.check
      address output
      format.edition output
      format.date "year" output.check
    }
    { format.incoll.inproc.crossref output.nonnull
      format.chapter.pages output
    }
  if$
  format.isbn output
  new.block
  note output
  fin.entry
}

FUNCTION {inproceedings}
{ output.bibitem
  format.authors "author" output.check
  author format.key output
  new.block
  format.title "title" output.check
  new.block
  crossref missing$
    { format.in.ed.booktitle "booktitle" output.check
      format.bvolume output
      format.number.series output
      format.pages output
      address output
      new.sentence
      organization output
      publisher output
  format.date "year" output.check
    }
    { format.incoll.inproc.crossref output.nonnull
      format.pages output
    }
  if$
  format.isbn output
  new.block
  note output
  fin.entry
}

FUNCTION {conference} { inproceedings }

FUNCTION {manual}
{ output.bibitem
  format.authors output
  author format.key output
  new.block
  format.btitle "title" output.check
  organization address new.block.checkb
  organization output
  address output
  format.edition output
  format.date "year" output.check
  new.block
  note output
  fin.entry
}

FUNCTION {mastersthesis}
{ output.bibitem
  format.authors "author" output.check
  author format.key output
  new.block
  format.btitle "title" output.check
  new.block
  bbl.mthesis format.thesis.type output.nonnull
  school "school" output.check
  address output
  format.date "year" output.check
  new.block
  note output
  fin.entry
}

FUNCTION {misc}
{ output.bibitem
  format.authors output
  author format.key output
  new.block
  format.title output
  new.block
  howpublished output
  format.date "year" output.check
  new.block
  note output
  fin.entry
}

FUNCTION {phdthesis}
{ output.bibitem
  format.authors "author" output.check
  author format.key output
  new.block
  format.btitle "title" output.check
  new.block
  bbl.phdthesis format.thesis.type output.nonnull
  school "school" output.check
  address output
  format.date "year" output.check
  new.block
  note output
  fin.entry
}

FUNCTION {proceedings}
{ output.bibitem
  format.editors output
  editor format.key output
  new.block
  format.btitle "title" output.check
  format.bvolume output
  format.number.series output
  address output
  new.sentence
  organization output
  publisher output
  format.date "year" output.check
  format.isbn output
  new.block
  note output
  fin.entry
}

FUNCTION {techreport}
{ output.bibitem
  format.authors "author" output.check
  author format.key output
  new.block
  format.title "title" output.check
  new.block
  format.tr.number output.nonnull
  institution "institution" output.check
  address output
  format.date "year" output.check
  new.block
  note output
  fin.entry
}

FUNCTION {unpublished}
{ output.bibitem
  format.authors "author" output.check
  author format.key output
  new.block
  format.title "title" output.check
  new.block
  note "note" output.check
  fin.entry
}

FUNCTION {default.type} { misc }

READ

FUNCTION {sortify}
{ purify$
  "l" change.case$
}

INTEGERS { len }

FUNCTION {chop.word}
{ 's :=
  'len :=
  s #1 len substring$ =
    { s len #1 + global.max$ substring$ }
    's
  if$
}

FUNCTION {format.lab.names}
{ 's :=
  s #1 "{vv~}{ll}" format.name$
  s num.names$ duplicate$
  #2 >
    { pop$ " et~al." * }
    { #2 <
        'skip$
        { s #2 "{ff }{vv }{ll}{ jj}" format.name$ "others" =
            { " et~al." * }
            { bbl.and
              space.word * s #2 "{vv~}{ll}" format.name$ * }
          if$
        }
      if$
    }
  if$
}

FUNCTION {author.key.label}
{ author empty$
    { key empty$
        { cite$ #1 #3 substring$ }
        'key
      if$
    }
    { author format.lab.names }
  if$
}

FUNCTION {author.editor.key.label}
{ author empty$
    { editor empty$
        { key empty$
            { cite$ #1 #3 substring$ }
            'key
          if$
        }
        { editor format.lab.names }
      if$
    }
    { author format.lab.names }
  if$
}

FUNCTION {editor.key.label}
{ editor empty$
    { key empty$
        { cite$ #1 #3 substring$ }
        'key
      if$
    }
    { editor format.lab.names }
  if$
}

FUNCTION {calc.short.authors}
{ type$ "book" =
  type$ "inbook" =
  or
    'author.editor.key.label
    { type$ "proceedings" =
        'editor.key.label
        'author.key.label
      if$
    }
  if$
  'short.list :=
}

FUNCTION {calc.label}
{ calc.short.authors
  short.list
  "("
  *
  year duplicate$ empty$
     { pop$ "????" }
     'skip$
  if$
  *
  'label :=
}

FUNCTION {sort.format.names}
{ 's :=
  #1 'nameptr :=
  ""
  s num.names$ 'numnames :=
  numnames 'namesleft :=
    { namesleft #0 > }
    { s nameptr
      "{ll{ }}{  ff{ }}{  jj{ }}"
      format.name$ 't :=
      nameptr #1 >
        {
          "   "  *
          namesleft #1 = t "others" = and
            { "aaaaa" * }
            { t sortify * }
          if$
        }
        { t sortify * }
      if$
      nameptr #1 + 'nameptr :=
      namesleft #1 - 'namesleft :=
    }
  while$
}

FUNCTION {sort.format.title}
{ 't :=
  "A " #2
    "An " #3
      "The " #4 t chop.word
    chop.word
  chop.word
  sortify
  #1 global.max$ substring$
}

FUNCTION {author.sort}
{ author empty$
    { key empty$
        { "to sort, need author or key in " cite$ * warning$
          ""
        }
        { key sortify }
      if$
    }
    { author sort.format.names }
  if$
}

FUNCTION {author.editor.sort}
{ author empty$
    { editor empty$
        { key empty$
            { "to sort, need author, editor, or key in " cite$ * warning$
              ""
            }
            { key sortify }
          if$
        }
        { editor sort.format.names }
      if$
    }
    { author sort.format.names }
  if$
}

FUNCTION {editor.sort}
{ editor empty$
    { key empty$
        { "to sort, need editor or key in " cite$ * warning$
          ""
        }
        { key sortify }
      if$
    }
    { editor sort.format.names }
  if$
}

FUNCTION {presort}
{ calc.label
  label sortify
  "    "
  *
  type$ "book" =
  type$ "inbook" =
  or
    'author.editor.sort
    { type$ "proceedings" =
        'editor.sort
        'author.sort
      if$
    }
  if$
  #1 entry.max$ substring$
  'sort.label :=
  sort.label
  *
  "    "
  *
  title field.or.null
  sort.format.title
  *
  #1 entry.max$ substring$
  'sort.key$ :=
}

ITERATE {presort}

SORT

STRINGS { last.label next.extra }

INTEGERS { last.extra.num number.label }

FUNCTION {initialize.extra.label.stuff}
{ #0 int.to.chr$ 'last.label :=
  "" 'next.extra :=
  #0 'last.extra.num :=
  #0 'number.label :=
}

FUNCTION {forward.pass}
{ last.label label =
    { last.extra.num #1 + 'last.extra.num :=
      last.extra.num int.to.chr$ 'extra.label :=
    }
    { "a" chr.to.int$ 'last.extra.num :=
      "" 'extra.label :=
      label 'last.label :=
    }
  if$
  number.label #1 + 'number.label :=
}

FUNCTION {reverse.pass}
{ next.extra "b" =
    { "a" 'extra.label := }
    'skip$
  if$
  extra.label 'next.extra :=
  extra.label
  duplicate$ empty$
    'skip$
    { "{" swap$ * "}" * }
  if$
  'extra.label :=
  label extra.label * 'label :=
}

EXECUTE {initialize.extra.label.stuff}

ITERATE {forward.pass}

REVERSE {reverse.pass}

FUNCTION {bib.sort.order}
{ sort.label
  "    "
  *
  year field.or.null sortify
  *
  "    "
  *
  title field.or.null
  sort.format.title
  *
  #1 entry.max$ substring$
  'sort.key$ :=
}

ITERATE {bib.sort.order}

SORT

FUNCTION {begin.bib}
{ preamble$ empty$
    'skip$
    { preamble$ write$ newline$ }
  if$
  "\begin{thebibliography}{" number.label int.to.str$ * "}" *
  write$ newline$
}

EXECUTE {begin.bib}

EXECUTE {init.state.consts}

ITERATE {call.type$}

FUNCTION {end.bib}
{ newline$
  "\end{thebibliography}" write$ newline$
}

EXECUTE {end.bib}
%% End of customized bst file 
%% 
%% End of file `peter.bst'.
