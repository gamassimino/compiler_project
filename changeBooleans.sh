for i in {1..117}
do
  sed -i -e 's/boolean/bool/g' test/correct/test${i}.plum
done