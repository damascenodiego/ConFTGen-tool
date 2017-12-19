(define-sort Feature () Bool)
(declare-const G Feature)
(declare-const A Feature)
(declare-const M Feature)
(declare-const L Feature)
(declare-const C Feature)
(declare-const R Feature)
(declare-const B Feature)
(declare-const N Feature)
(declare-const W Feature)
(declare-const V Feature)
(declare-const Y Feature)
(declare-const P Feature)
(declare-const S Feature)

(assert G)
(assert (and
   (= A G)
   (= M A)
   (= L A)
   (= C G)
   (= R G)
   (= (or B  N  W ) R)
   (not (and B N))
   (not (and B W))
   (not (and N W))
   (= V G)
   (= Y V)
   (= P V)
   (=> S V)
))

(push)
(assert S)
(assert (and true true B S true N ))
(check-sat)
(pop)
