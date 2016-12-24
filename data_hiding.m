% Last modified: 3/24/2016 by Zhe
% This function embeds data into a binary vector
% Input:  image_serial:    binary vector into which we embed data
%         info:            data that we embed into image_serial
%         k:               embedding block size (referring to "Embedding Data with Minor Modifications" part)
% Output: changed_serial:  embedded binary vector

function [changed_serial] = data_hiding(image_serial, info, k) 

% some settings
image_serial_length = length(image_serial);

img_seri_array = zeros(ceil(image_serial_length/k), k);
for i = 1:1:floor(image_serial_length/k)
    img_seri_array(i,:) = image_serial((i-1)*k+1:i*k); % map vector into matrix
end
if floor(image_serial_length/k)*k ~= image_serial_length % if there is remainder, we adhere the remaining few values at the end of the matrix
    img_seri_array(ceil(image_serial_length/k), 1:image_serial_length-floor(image_serial_length/k)*k) = image_serial(floor(image_serial_length/k)*k+1:image_serial_length);
end
changed_serial_array = img_seri_array;
W = zeros(1,k); %  serial number vector
W_max = 2^(floor(log2(k+1)))-1;
bits_in_one = floor(log2(k+1)); % number of bits that can be embedded into one block
for i = 1:1:W_max
    W(i) = i;
end
if W_max ~= k
    for j = 1:1:k-W_max
        W(W_max+j) = j;
    end
end



% change info into binary n*1 array
info_double = double(info);
info_bin = dec2bin(info_double,8);
info_bin = info_bin';
info_serial = info_bin(:);
info_serial = info_serial';


embed_serial_num = ceil(length(info_serial)/bits_in_one);
% embed data block by block, according to the steps in "Embedding Data with Minor Modifications"
for i = 1:1:embed_serial_num
    W_binary = dec2bin(W); 
    [~, W_bits] = size(W_binary);
    if bits_in_one*i<= length(info_serial)
        info_cell = info_serial((i-1)*bits_in_one+1:bits_in_one*i);
    else
        info_cell = zeros(1,bits_in_one);
        info_cell(1:length(info_serial)-(i-1)*bits_in_one) = info_serial((i-1)*bits_in_one+1:length(info_serial));
    end
    % H
    H = zeros(1, bits_in_one);
    for ii = 1:1:k
        for jj = 1:1:W_bits
            if W_binary(ii,jj) == '1' && img_seri_array(i, ii) == 1
                H(jj) = H(jj) + 1;
            end
        end
    end
    % h
    h = zeros(1,bits_in_one);
    for ii = 1:1:bits_in_one
        h(ii) = mod(H(ii), 2);
    end
    % R1, R2, R3, R4
    R = zeros(1,bits_in_one);
    for ii = 1:1:bits_in_one
        if info_cell(ii)-'0' ~= h(ii)-0
            R(ii) = 1;
        end
    end
    % MP
    MP = 0;
    for ii = 1:1:bits_in_one
        MP = MP + 2^(ii-1) * R(bits_in_one-ii+1);
    end
    if MP == 0
        continue;
    else
        changed_serial_array(i, MP) = mod(changed_serial_array(i, MP)+1 ,2); % embed data by toggling the bit
    end

end

changed_serial_t = reshape(changed_serial_array',1,ceil(image_serial_length/k)*k);
changed_serial = changed_serial_t(1:image_serial_length);
end
