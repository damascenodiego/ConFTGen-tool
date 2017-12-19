(define-sort Feature () Bool)
(declare-const A Feature)
(declare-const  Feature)
(declare-const B Feature)
(declare-const  Feature)
(declare-const U Feature)
(declare-const  Feature)
(declare-const Y Feature)
(declare-const  Feature)
(declare-const C Feature)
(declare-const  Feature)
(declare-const T Feature)
(declare-const  Feature)
(declare-const L Feature)
(declare-const  Feature)
(declare-const N Feature)
(declare-const  Feature)
(declare-const W Feature)
(declare-const  Feature)

(assert A)
(assert (and
   (=>  A)
   (= B A)
   (=>  B)
   (=> U B)
   (=>  U)
   (= Y B)
   (=>  C)
   (=>  T)
   (= (or   C  T ) Y)
   (not (and  C))
   (not (and  T))
   (not (and C T))
   (= L A)
   (=>  L)
   (= N L)
   (=>  N)
   (= W L)
   (=>  W)
))

(push)
(assert U)
(assert (and true true true C (or C T) true ))
(check-sat)
(pop)
(push)
(assert U)
(assert (and true true true T (or C T) true ))
(check-sat)
(pop)
(push)
(assert (or C T))
(assert (and true true true C ))
(check-sat)
(pop)
(push)
(assert (or C T))
(assert (and true true true T ))
(check-sat)
(pop)
(push)
(assert true)
(assert (and true true ))
(check-sat)
(pop)