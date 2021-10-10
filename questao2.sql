select Sum(hor.numhoras), pro.codprof from professor pro
join profturma pt on pt.codprof  = pro.codprof
join turma tur on pt.coddepto = tur.coddepto and
pt.numdisc = tur.numdisc and pt.anosem = tur.anosem and
pt.siglatur = tur.siglatur 
join horario hor on tur.siglatur = hor.siglatur and
tur.coddepto = hor.coddepto and tur.numdisc = hor.numdisc and
tur.anosem = hor.anosem 
group by pro.codprof;