(define-sort Feature () Bool)
(declare-const A Feature)
(declare-const B Feature)
(declare-const U Feature)
(declare-const Y Feature)
(declare-const C Feature)
(declare-const T Feature)
(declare-const L Feature)
(declare-const N Feature)
(declare-const W Feature)

(assert A)
(assert (and
   (= B A)
   (=> U B)
   (= Y B)
   (= (or C  T ) Y)
   (not (and C T))
   (= L A)
   (= N L)
   (= W L)
))


 (assert (and A B Y T L N W ))(assert (and U (or C T)))
(assert (and 
    (not (and true true true U ))
))
(check-sat)