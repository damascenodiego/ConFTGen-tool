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

(push)
(assert (and true true))
(check-sat)
(pop)

(push)
(assert true)
(assert (and (not true)))
(check-sat)
(pop)

