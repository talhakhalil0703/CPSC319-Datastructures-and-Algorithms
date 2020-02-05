data = readmatrix("matrix.csv")
filtered = medfilt1(data,3);
x = max(filtered);

plot (filtered(:,end))


ylim([0 x(2)])

saveas(gcf,"plot1.png")